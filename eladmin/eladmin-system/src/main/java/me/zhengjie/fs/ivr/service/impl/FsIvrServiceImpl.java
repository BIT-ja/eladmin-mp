/*
*  Copyright 2019-2023 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.fs.ivr.service.impl;

import me.zhengjie.fs.ivr.domain.FsIvr;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhengjie.fs.ivr.service.FsIvrService;
import me.zhengjie.fs.ivr.domain.vo.FsIvrQueryCriteria;
import me.zhengjie.fs.ivr.mapper.FsIvrMapper;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import me.zhengjie.utils.PageUtil;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import me.zhengjie.utils.PageResult;

/**
* @description 服务实现
* @author caius
* @date 2024-12-21
**/
@Service
@RequiredArgsConstructor
public class FsIvrServiceImpl extends ServiceImpl<FsIvrMapper, FsIvr> implements FsIvrService {

    private final FsIvrMapper fsIvrMapper;

    @Override
    public PageResult<FsIvr> queryAll(FsIvrQueryCriteria criteria, Page<Object> page){
        return PageUtil.toPage(fsIvrMapper.findAll(criteria, page));
    }

    @Override
    public List<FsIvr> queryAll(FsIvrQueryCriteria criteria){
        return fsIvrMapper.findAll(criteria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(FsIvr resources) {
        resources.setCreateBy(SecurityUtils.getCurrentUserId().toString());
        resources.setCreateTime(new Timestamp(System.currentTimeMillis()));
        save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(FsIvr resources) {
        FsIvr fsIvr = getById(resources.getId());
        fsIvr.copy(resources);
        saveOrUpdate(fsIvr);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(List<String> ids) {
        removeBatchByIds(ids);
    }

    @Override
    public void download(List<FsIvr> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (FsIvr fsIvr : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("被叫小号", fsIvr.getDestNum());
            map.put("版本号", fsIvr.getTag());
            map.put("创建日期", fsIvr.getCreateTime());
            map.put("创建人", fsIvr.getCreateBy());
            map.put("更新日期", fsIvr.getUpdateTime());
            map.put("更新人", fsIvr.getUpdateBy());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}