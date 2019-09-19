package com.gly.testfreemarker.controller;

import com.gly.testfreemarker.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RequestMapping("/freemarker")
@Controller
@Slf4j
public class FreemarkerController {

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping("banner")
    public String index_banner(Map<String, Object> map) {
        try {
            ResponseEntity<Map> template = restTemplate.getForEntity("http://localhost:31001/cms/config/getmodel/5a791725dd573c3574ee333f", Map.class);
            Map body = template.getBody();
            map.putAll(body);
        } catch (Exception e) {
            log.error("e : {}", e);
        }
        return "index_banner";
    }

    @RequestMapping("/test1")
    public String freemarker(Map<String, Object> map) {
        //向数据模型放数据
        map.put("name","程序员");

        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);
        // stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus",stus);
        //准备map数据
        HashMap<String,Student> stuMap = new HashMap<>();
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);
        //向数据模型放数据
        map.put("stu1",stu1);
        //向数据模型放数据
        map.put("stuMap",stuMap);

        //返回模板文件名称
        return "test1";
    }
}
