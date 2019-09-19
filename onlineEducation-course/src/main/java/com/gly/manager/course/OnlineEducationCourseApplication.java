package com.gly.manager.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootApplication
@EntityScan("com.gly.common.model.course")//扫描实体类
@ComponentScan(basePackages = {"com.gly.api"})//扫描接口
@ComponentScan(basePackages = {"com.gly.common"})//扫描common下的所有类
@ComponentScan(basePackages = {"com.gly.manager.course"})//扫描接口
@EnableDiscoveryClient
@EnableFeignClients
public class OnlineEducationCourseApplication {

    public static void main(String[] args){
        SpringApplication.run(OnlineEducationCourseApplication.class, args);
    }
}
