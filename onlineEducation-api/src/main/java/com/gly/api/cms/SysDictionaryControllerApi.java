package com.gly.api.cms;

import com.gly.common.model.system.SysDictionary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/16
 * @since 1.0.0
 */
@Api(value = "数据字典接口",description = "提供数据字典接口的管理、查询功能")
public interface SysDictionaryControllerApi {

    //数据字典
    @ApiOperation(value="数据字典查询接口")
    SysDictionary getByType(String type);
}