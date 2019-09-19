package com.gly.api.cms;


import com.gly.common.model.cms.CmsPage;
import com.gly.common.model.cms.request.QueryPageRequest;
import com.gly.common.model.cms.response.CmsPageResult;
import com.gly.common.model.course.CourseBase;
import com.gly.common.model.response.QueryResponseResult;
import com.gly.common.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {

    //页面查询
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value = "页码", required=true, paramType="path",dataType="int"),
            @ApiImplicitParam(name="size", value = "每页记录数", required=true,paramType="path", dataType="int")
    })
    QueryResponseResult<CourseBase> findList(int page, int size, QueryPageRequest queryPageRequest);

    //新增页面
    @ApiOperation("新增页面")
    CmsPageResult add(CmsPage cmsPage);

    //根据页面id查询页面信息
    @ApiOperation("根据页面id查询页面信息")
    CmsPage findById(String id);
    //修改页面
    @ApiOperation("修改页面")
    CmsPageResult edit(String id,CmsPage cmsPage);

    //删除页面
    @ApiOperation("删除页面")
    ResponseResult delete(String id);

    @ApiOperation("发布页面")
    ResponseResult post(String pageId);
}
