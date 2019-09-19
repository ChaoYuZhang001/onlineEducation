package com.gly.api.cms;

import com.gly.common.model.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 〈一句话功能简述〉<br>
 * 〈cms配置管理接口API〉
 *
 * @author zcy
 * @create 2019/8/28
 * @since 1.0.0
 */
@Api(value = "cms配置管理接口", description = "cms配置管理接口，提供数据模型的管理、查询接口")
public interface CmsConfigControllerApi {

    @ApiOperation("根据id查询CMS配置信息")
    CmsConfig getmodel(String id);

}