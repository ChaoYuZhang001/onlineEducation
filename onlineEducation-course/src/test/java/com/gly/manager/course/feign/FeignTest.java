package com.gly.manager.course.feign;

import com.gly.common.model.cms.CmsPage;
import com.gly.manager.course.OnlineEducationCourseApplicationTest;
import com.gly.manager.course.client.CmsPageClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author zcy
 * @create 2019/9/18
 * @since 1.0.0
 */
public class FeignTest extends OnlineEducationCourseApplicationTest {

    @Autowired
    CmsPageClient cmsPageClient;

    //    Feign是Netflix公司开源的轻量级rest客户端，使用Feign可以非常方便的实现Http 客户端。
    //    Spring Cloud引入  Feign并且集成了Ribbon实现客户端负载均衡调用。
    @Test
    public void testFeign() {
        //通过服务id调用cms的查询页面接口
        CmsPage cmsPage = cmsPageClient.findById("5a795ac7dd573c04508f3a56");
        System.out.println(cmsPage);
    }
}