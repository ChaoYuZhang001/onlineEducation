package com.gly.manager.course.dao;


import com.github.pagehelper.Page;
import com.gly.common.model.course.CourseBase;
import com.gly.common.model.course.ext.CourseInfo;
import com.gly.common.model.course.request.CourseListRequest;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by zcy.
 */
@Mapper
public interface CourseMapper {

    CourseBase findCourseBaseById(String id);

    Page<CourseInfo> findCourseListPage(CourseListRequest courseListRequest);
}
