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
package me.zhengjie.fs.ivr.domain.vo;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

/**
* @author caius
* @date 2024-12-21
**/
@Data
public class FsIvrQueryCriteria{
    private String id;
    private String destNum;
    private String tag;
    private String createBy;
    private String updateBy;
    private List<Timestamp> createTime;
    private List<Timestamp> updateTime;
}