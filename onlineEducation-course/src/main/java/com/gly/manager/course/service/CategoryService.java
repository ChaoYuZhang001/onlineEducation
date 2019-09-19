package com.gly.manager.course.service;

import com.gly.common.model.course.ext.CategoryNode;
import com.gly.manager.course.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/16
 * @since 1.0.0
 */
@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    //查询分类
    public CategoryNode findList(){
        return categoryMapper.selectList();
    }

}