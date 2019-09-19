package com.gly.testfreemarker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TestFreemarkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestFreemarkerApplication.class, args);
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
