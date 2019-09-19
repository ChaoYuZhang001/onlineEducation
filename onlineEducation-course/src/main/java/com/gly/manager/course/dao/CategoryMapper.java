package com.gly.manager.course.dao;

import com.gly.common.model.course.ext.CategoryNode;
import org.apache.ibatis.annotations.Mapper;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/16
 * @since 1.0.0
 */
@Mapper
public interface CategoryMapper {

    //查询分类
    CategoryNode selectList();
}