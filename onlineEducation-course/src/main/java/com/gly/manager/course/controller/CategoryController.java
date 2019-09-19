/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CategoryController
 * Author:   Administrator
 * Date:     2019/9/16 15:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.gly.manager.course.controller;

import com.gly.api.course.CategoryControllerApi;
import com.gly.common.model.course.ext.CategoryNode;
import com.gly.manager.course.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/16
 * @since 1.0.0
 */
@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryControllerApi {

    @Autowired
    CategoryService categoryService;

    @Override
    @GetMapping("/list")
     public CategoryNode findList() {
        return categoryService.findList();
    }

}