/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: RibbonTest
 * Author:   Administrator
 * Date:     2019/9/18 16:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.gly.manager.course.ribbon;

import com.gly.common.model.cms.CmsPage;
import com.gly.manager.course.OnlineEducationCourseApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2019/9/18
 * @since 1.0.0
 */
public class RibbonTest extends OnlineEducationCourseApplicationTest {

    @Autowired
    RestTemplate restTemplate;

    //    Spring Cloud引入Ribbon配合 restTemplate 实现客户端负载均衡。Java中远程调用的技术有很多，如：
    //    webservice、socket、rmi、Apache HttpClient、OkHttp等，互联网项目使用基于http的客户端较多，本项目使用OkHttp。

    //负载均衡调用
    @Test
    public void testRibbon() {
        //服务id
        String serviceId = "onlineEducation-cms";
        for (int i = 0; i < 10; i++) {
            //通过服务id调用
            ResponseEntity<CmsPage> forEntity = restTemplate.getForEntity("http://" + serviceId
                    + "/cms/page/get/5a754adf6abb500ad05688d9", CmsPage.class);
            CmsPage cmsPage = forEntity.getBody();
            System.out.println(cmsPage);
        }
    }
}