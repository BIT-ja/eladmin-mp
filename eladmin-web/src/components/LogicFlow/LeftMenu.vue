<template>
  <ul class="diagram-sidebar">
    <li v-for="groupItem in leftDataList" :key="groupItem.key">
      <h1 class="node-category-title">{{ groupItem.groupName }}</h1>
      <template v-if="Array.isArray(groupItem.nodes) && groupItem.nodes.length > 0">
        <div
          v-for="item in groupItem.nodes"
          :key="item.type"
          class="left-bar-item"
          :style="`backgroundColor:${item.backgroundColor}; border:${item.border}`"
          @mousedown="dragInNode(item.type)"
        >
          <svg-icon icon-class="qiniu" :style="`color:${item.borderColor};`" />
          <p class="name" :style="`color:${item.borderColor};`">{{ item.name }}</p>
        </div>
      </template>
    </li>
  </ul>
</template>

<script>

export default ({
  name: 'LeftMenu',
  props: {
    taskDetail: {
      type: Object,
      default: () => ({})
    }
  },
  data() {
    return {
      leftDataList: [
        {
          groupName: '分组-1',
          key: 'marketing',
          nodes: [
            {
              type: 'SINGLE_CROWD',
              borderColor: 'rgba(71, 98, 254, 1)',
              backgroundColor: 'rgba(71, 98, 254, 0.1)',
              border: 'solid 1px rgba(71, 98, 254, 1)',
              name: '单个信息'
            },
            {
              type: 'BATCH_CROWD',
              borderColor: 'rgba(71, 98, 254, 1)',
              backgroundColor: 'rgba(71, 98, 254, 0.1)',
              border: 'solid 1px rgba(71, 98, 254, 1)',
              name: '批量信息'
            },
            {
              type: 'RULE_CROWD',
              borderColor: 'rgba(71, 98, 254, 1)',
              backgroundColor: 'rgba(71, 98, 254, 0.1)',
              border: 'solid 1px rgba(71, 98, 254, 1)',
              name: '信息规则'
            }
          ]
        },
        {
          groupName: '分组-2',
          key: 'target',
          nodes: [
            {
              type: 'GOAL_CROWD',
              borderColor: 'rgba(64, 158, 255, 1)',
              backgroundColor: 'rgba(64, 158, 255, 0.1)',
              border: 'solid 1px rgba(64, 158, 255, 1)',
              name: '目标'
            }
          ]
        },
        {
          groupName: '分组-3',
          key: 'compute',
          nodes: [
            {
              type: 'MERGE',
              borderColor: 'rgba(255, 180, 30, 1)',
              backgroundColor: 'rgba(255, 180, 30, 0.15)',
              border: 'solid 1px rgba(255, 180, 30, 1)',
              name: '合并'
            },
            {
              type: 'SPLIT',
              borderColor: 'rgba(255, 180, 30, 1)',
              backgroundColor: 'rgba(255, 180, 30, 0.15)',
              border: 'solid 1px rgba(255, 180, 30, 1)',
              name: '拆分'
            },
            {
              type: 'UNIQUE',
              borderColor: 'rgba(255, 180, 30, 1)',
              backgroundColor: 'rgba(255, 180, 30, 0.15)',
              border: 'solid 1px rgba(255, 180, 30, 1)',
              name: '排重'
            },
            {
              type: 'AND',
              borderColor: 'rgba(255, 180, 30, 1)',
              backgroundColor: 'rgba(255, 180, 30, 0.15)',
              border: 'solid 1px rgba(255, 180, 30, 1)',
              name: '交集'
            },
            {
              type: 'EXCLUDE',
              borderColor: 'rgba(255, 180, 30, 1)',
              backgroundColor: 'rgba(255, 180, 30, 0.15)',
              border: 'solid 1px rgba(255, 180, 30, 1)',
              name: '排除'
            },
            {
              type: 'SPREAD',
              borderColor: 'rgba(255, 180, 30, 1)',
              backgroundColor: 'rgba(255, 180, 30, 0.15)',
              border: 'solid 1px rgba(255, 180, 30, 1)',
              name: '智能扩散'
            },
            {
              type: 'SORT',
              borderColor: 'rgba(255, 180, 30, 1)',
              backgroundColor: 'rgba(255, 180, 30, 0.15)',
              border: 'solid 1px rgba(255, 180, 30, 1)',
              name: '高能排序'
            }
          ]
        },
        {
          groupName: '分组-4',
          key: 'touch',
          nodes: [
            {
              type: 'DONGDONG',
              borderColor: 'rgba(0, 200, 100, 1)',
              backgroundColor: 'rgba(0, 200, 100, 0.1)',
              border: 'solid 1px rgba(0, 200, 100, 1)',
              name: '消息'
            },
            {
              type: 'SMS',
              borderColor: 'rgba(0, 200, 100, 1)',
              backgroundColor: 'rgba(0, 200, 100, 0.1)',
              border: 'solid 1px rgba(0, 200, 100, 1)',
              name: '短信'
            }
          ]
        }
      ]
    }
  },
  computed: {

    // 拖拽和禁用拖拽按钮状态
    getClassFn() {
      return this.canDrag ? 'cursor-not-allowed' : 'cursor-pointer'
    },

    // 判断是否可被拖拽（先注释）
    // eslint-disable-next-line vue/return-in-computed-property
    canDrag() {
      // return this.canvasType === 'detail' || CanNotEdit.includes(this.taskDetail?.canvasStatus?.toString());
    },

    canvasType() {
      const QUERY_TYPE = this.$router.history.current.query.type
      console.log('QUERY_TYPE::', QUERY_TYPE)
      return QUERY_TYPE
    }

  },
  mounted() {

  },
  methods: {
    getMenuIconObj(item) {
      return {
        icon: item.icon,
        name: item.name
      }
    },
    // 动态设置样式
    getItemStyleFn(item) {
      return `border: 1px solid ${item.borderColor}; background-color: ${item.backgroundColor};`
    },
    // 数据按下事件，主要是获取菜单的type类型
    dragInNode(type) {
      const that = this
      console.log('dragInNode', that.canDrag, that.taskDetail)
      if (that.canDrag) return

      // that.$emit('dragInNode', {
      //     type,
      //     properties: {
      //         ...InitNodeData[type],
      //     },
      // });
    }
  }
})
</script>

<style scoped>
.diagram-sidebar {
  user-select: none;
  flex-basis: 200px;
  padding: 13px 25px;

  .node-category-title {
    font-size: 14px;
    font-weight: 600;
    line-height: 22px;
    text-align: left;
    margin-bottom: 12px;
  }

  .left-bar-item{
    width: 140px;
    height: 38px;
    border-radius: 8px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
    gap: 8px;
    margin-bottom: 12px;
    padding-left: 24px;
    .svg-icon{
      width: 16px;
      height: 16px;
    }
    .name{
      font-size: 14px;
      font-weight: 400;
      line-height: 22px;
      text-align: left;
    }
  }
}
</style>
