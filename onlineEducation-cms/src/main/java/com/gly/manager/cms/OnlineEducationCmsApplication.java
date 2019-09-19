package com.gly.manager.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan("com.gly.common.model")//扫描实体类
@ComponentScan(basePackages={"com.gly.api"})//扫描接口
@ComponentScan(basePackages={"com.gly.common"})//扫描common下的所有类
@ComponentScan(basePackages={"com.gly.manager.cms"})//扫描接口
@EnableDiscoveryClient
public class OnlineEducationCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineEducationCmsApplication.class, args);
    }

//    SpringMVC提供 RestTemplate请求http接口，RestTemplate的底层可以使用第三方的http客户端工具实现http 的
//    请求，常用的http客户端工具有Apache HttpClient、OkHttpClient等，本项目使用OkHttpClient完成http请求，
//    原因也是因为它的性能比较出众。
    //在SpringBoot启动类中配置 RestTemplate
    @Bean
    public RestTemplate RestTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
