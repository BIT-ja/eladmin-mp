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
package me.zhengjie.fs.ivr.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.fs.ivr.domain.FsIvr;
import me.zhengjie.fs.ivr.service.FsIvrService;
import me.zhengjie.fs.ivr.domain.vo.FsIvrQueryCriteria;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zhengjie.utils.PageResult;

/**
* @author caius
* @date 2024-12-21
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "ivr_list管理")
@RequestMapping("/api/fsIvr")
public class FsIvrController {

    private final FsIvrService fsIvrService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('fsIvr:list')")
    public void exportFsIvr(HttpServletResponse response, FsIvrQueryCriteria criteria) throws IOException {
        fsIvrService.download(fsIvrService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询ivr_list")
    @ApiOperation("查询ivr_list")
    @PreAuthorize("@el.check('fsIvr:list')")
    public ResponseEntity<PageResult<FsIvr>> queryFsIvr(FsIvrQueryCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(fsIvrService.queryAll(criteria,page),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增ivr_list")
    @ApiOperation("新增ivr_list")
    @PreAuthorize("@el.check('fsIvr:add')")
    public ResponseEntity<Object> createFsIvr(@Validated @RequestBody FsIvr resources){
        fsIvrService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改ivr_list")
    @ApiOperation("修改ivr_list")
    @PreAuthorize("@el.check('fsIvr:edit')")
    public ResponseEntity<Object> updateFsIvr(@Validated @RequestBody FsIvr resources){
        fsIvrService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除ivr_list")
    @ApiOperation("删除ivr_list")
    @PreAuthorize("@el.check('fsIvr:del')")
    public ResponseEntity<Object> deleteFsIvr(@RequestBody List<String> ids) {
        fsIvrService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}