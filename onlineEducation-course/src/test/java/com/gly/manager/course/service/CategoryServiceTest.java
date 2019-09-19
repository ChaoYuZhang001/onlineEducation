package com.gly.manager.course.service;

import com.gly.common.model.course.ext.CategoryNode;
import com.gly.manager.course.OnlineEducationCourseApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class CategoryServiceTest extends OnlineEducationCourseApplicationTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void findList() {
        CategoryNode categoryNode = categoryService.findList();
        System.out.println(categoryNode);
        if (!Objects.isNull(categoryNode)) {
            List<CategoryNode> children = categoryNode.getChildren();

            for (CategoryNode child : children) {
                System.out.println(child);
            }
        }
    }
}