package com.gly.manager.course.dao;

import com.gly.common.model.course.Teachplan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/9/16
 * @since 1.0.0
 */
public interface TeachplanRepository extends JpaRepository<Teachplan, String> {

    //定义方法根据课程id和父结点id查询出结点列表，可以使用此方法实现查询根结点
    List<Teachplan> findByCourseidAndParentid(String courseId, String parentId);
}