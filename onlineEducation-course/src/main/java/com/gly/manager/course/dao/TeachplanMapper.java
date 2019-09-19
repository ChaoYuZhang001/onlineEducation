package com.gly.manager.course.dao;

import com.gly.common.model.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/12
 * @since 1.0.0
 */
@Mapper
public interface TeachplanMapper {

    TeachplanNode selectList(@Param("courseId") String courseId);
}