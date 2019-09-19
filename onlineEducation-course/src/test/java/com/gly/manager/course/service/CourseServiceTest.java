package com.gly.manager.course.service;

import com.gly.common.model.course.ext.TeachplanNode;
import com.gly.manager.course.OnlineEducationCourseApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class CourseServiceTest extends OnlineEducationCourseApplicationTest {

    @Autowired
    CourseService courseService;

    @Test
    public void findTeachplanList() {
        String courseId = "4028e581617f945f01617f9dabc40000";

        TeachplanNode teachplanNode = courseService.findTeachplanList(courseId);

        System.out.println(teachplanNode);
    }
}