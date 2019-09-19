package com.gly.manager.course.dao;

import com.gly.common.model.course.CoursePic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/18
 * @since 1.0.0
 */
public interface CoursePicRepository extends JpaRepository<CoursePic, String> {

    long deleteByCourseid(String courseId);
}