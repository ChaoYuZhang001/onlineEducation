package com.gly.manager.cms.service;

import com.gly.common.model.course.CourseBase;
import com.gly.common.model.response.QueryResponseResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsPageServiceTest {

    @Autowired
    private  CmsPageService pageService;

    @Test
    public void findList() {
        int page = 0;
        int size = 10;
        QueryResponseResult<CourseBase> list = pageService.findList(page, size, null);
    }

    @Test
    public void getPageHtml() {
        String pageHtml = pageService.getPageHtml("5a795ac7dd573c04508f3a56");
        System.out.println(pageHtml);
    }
}