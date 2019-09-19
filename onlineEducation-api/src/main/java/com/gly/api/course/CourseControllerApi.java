package com.gly.api.course;

import com.gly.common.model.course.CourseBase;
import com.gly.common.model.course.CourseMarket;
import com.gly.common.model.course.CoursePic;
import com.gly.common.model.course.Teachplan;
import com.gly.common.model.course.ext.CourseInfo;
import com.gly.common.model.course.ext.TeachplanNode;
import com.gly.common.model.course.request.CourseListRequest;
import com.gly.common.model.course.response.AddCourseResult;
import com.gly.common.model.response.QueryResponseResult;
import com.gly.common.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/12
 * @since 1.0.0
 */
@Api(value = "课程管理接口", description = "课程管理接口，提供页面的增、删、改、查")
public interface CourseControllerApi {

    @ApiOperation("课程计划查询")
    TeachplanNode findTeachPlanList(String courseId);

    @ApiOperation("添加课程计划")
    ResponseResult addTeachPlan(Teachplan teachplan);

    //查询课程列表
    @ApiOperation("查询我的课程列表")
    QueryResponseResult<CourseInfo> findCourseList(int page, int size, CourseListRequest courseListRequest);

    @ApiOperation("添加课程基础信息")
    AddCourseResult addCourseBase(CourseBase courseBase);

    @ApiOperation("获取课程基础信息")
    CourseBase getCourseBaseById(String courseId) throws RuntimeException;

    @ApiOperation("更新课程基础信息")
    ResponseResult updateCourseBase(String id,CourseBase courseBase);

    @ApiOperation("获取课程营销信息")
    CourseMarket getCourseMarketById(String courseId);

    @ApiOperation("更新课程营销信息")
    ResponseResult updateCourseMarket(String id,CourseMarket courseMarket);

    @ApiOperation("添加课程图片")
    ResponseResult addCoursePic(String courseId,String pic);

    @ApiOperation("获取课程图片基础信息")
    CoursePic findCoursePic(String courseId);

    @ApiOperation("删除课程图片")
     ResponseResult deleteCoursePic(String courseId);

}