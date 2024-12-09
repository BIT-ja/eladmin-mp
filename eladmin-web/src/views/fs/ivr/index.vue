<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">id</label>
        <el-input v-model="query.id" clearable placeholder="id" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">被叫小号</label>
        <el-input v-model="query.destNum" clearable placeholder="被叫小号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">版本号</label>
        <el-input v-model="query.tag" clearable placeholder="版本号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">创建人</label>
        <el-input v-model="query.createBy" clearable placeholder="创建人" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">更新人</label>
        <el-input v-model="query.updateBy" clearable placeholder="更新人" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <date-range-picker
          v-model="query.createTime"
          start-placeholder="createTimeStart"
          end-placeholder="createTimeStart"
          class="date-item"
        />
        <date-range-picker
          v-model="query.updateTime"
          start-placeholder="updateTimeStart"
          end-placeholder="updateTimeStart"
          class="date-item"
        />
        <rrOperation :crud="crud" />
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="id" prop="id">
            <el-input v-model="form.id" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="被叫小号" prop="destNum">
            <el-input v-model="form.destNum" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="版本号" prop="tag">
            <el-input v-model="form.tag" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="创建日期">
            <el-date-picker v-model="form.createTime" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="创建人">
            <el-input v-model="form.createBy" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="更新日期">
            <el-date-picker v-model="form.updateTime" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="更新人">
            <el-input v-model="form.updateBy" style="width: 370px;" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="id" label="id">
          <template slot-scope="scope">
            <router-link :to="'/develop/fs/ivr/flow/' + scope.row.id" class="blue-link">
              {{ scope.row.id }}
            </router-link>
          </template>
        </el-table-column>
        <el-table-column prop="destNum" label="被叫小号" />
        <el-table-column prop="tag" label="版本号" />
        <el-table-column prop="createTime" label="创建日期" />
        <el-table-column prop="createBy" label="创建人" />
        <el-table-column prop="updateTime" label="更新日期" />
        <el-table-column prop="updateBy" label="更新人" />
        <el-table-column v-if="checkPer(['admin','fsIvr:edit','fsIvr:del'])" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import crudFsIvr from '@/api/fs/ivr/fsIvr'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'
import DateRangePicker from 'vue2-daterange-picker'
import 'vue2-daterange-picker/dist/vue2-daterange-picker.css'

const defaultForm = { id: null, destNum: null, tag: null, createTime: null, createBy: null, updateTime: null, updateBy: null }
export default {
  name: 'FsIvr',
  components: { pagination, crudOperation, rrOperation, udOperation, DateRangePicker },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: 'ivr_list', url: 'api/fsIvr', idField: 'id', sort: 'id,desc', crudMethod: { ...crudFsIvr }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'fsIvr:add'],
        edit: ['admin', 'fsIvr:edit'],
        del: ['admin', 'fsIvr:del']
      },
      rules: {
        id: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        destNum: [
          { required: true, message: '被叫小号不能为空', trigger: 'blur' }
        ],
        tag: [
          { required: true, message: '版本号不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        { key: 'id', display_name: 'id' },
        { key: 'destNum', display_name: '被叫小号' },
        { key: 'tag', display_name: '版本号' },
        { key: 'createBy', display_name: '创建人' },
        { key: 'updateBy', display_name: '更新人' }
      ]
    }
  },
  methods: {
    // 钩子：在获取表格数据之前执行，false 则代表不获取数据
    [CRUD.HOOK.beforeRefresh]() {
      return true
    }
  }
}
</script>

<style scoped>
.blue-link {
  color: #409EFF;
}
</style>
