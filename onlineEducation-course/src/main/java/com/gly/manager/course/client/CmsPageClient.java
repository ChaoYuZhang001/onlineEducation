package com.gly.manager.course.client;

import com.gly.common.client.OeServiceList;
import com.gly.common.model.cms.CmsPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 * @author zcy
 * @create 2019/9/18
 * @since 1.0.0
 */
@FeignClient(value = OeServiceList.ONLINE_EDUCATION__CMS)
public interface CmsPageClient {

    //    Feign工作原理如下：
    //     1、 启动类添加@EnableFeignClients注解，Spring会扫描标记了@FeignClient注解的接口，并生成此接口的代理对象
    //     2、 @FeignClient(value = OeServiceList.ONLINE_EDUCATION__CMS)即指定了cms的服务名称，Feign会从注册中
    //      心获取cms服务列表，并通过负载均衡算法进行服务调用。
    //     3、在接口方法 中使用注解@GetMapping("/cms/page/get/{id}")，指定调用的url，Feign将根据url进行远程调用。

    @GetMapping("/cms/page/get/{id}")
    CmsPage findById(@PathVariable("id") String id);
}