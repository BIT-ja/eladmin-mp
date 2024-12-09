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
package me.zhengjie.fs.ivr.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import java.sql.Timestamp;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;

/**
* @description /
* @author caius
* @date 2024-12-21
**/
@Data
@TableName("fs_ivr")
public class FsIvr implements Serializable {

    @TableId(value = "id")
    @ApiModelProperty(value = "id")
    private String id;

    @NotBlank
    @ApiModelProperty(value = "被叫小号")
    private String destNum;

    @NotBlank
    @ApiModelProperty(value = "版本号")
    private String tag;

    @ApiModelProperty(value = "创建日期")
    private Timestamp createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private Timestamp updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    public void copy(FsIvr source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
