package com.gly.file_system;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.gly.common.model")//扫描实体类
@ComponentScan(basePackages = {"com.gly.api"})//扫描接口
@ComponentScan(basePackages = {"com.gly.common"})//扫描common下的所有类
@ComponentScan(basePackages = {"com.gly.file_system"})//扫描接口
public class BaseFileSystemApplication {
    public static void main(String[] args){
        SpringApplication.run(BaseFileSystemApplication.class, args);
    }
}