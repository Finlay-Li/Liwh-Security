package com.liwh.controller;

import com.liwh.dao.model.Admin;
import com.liwh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Liwh
 * @ClassName: DemoController
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-05 7:49 PM
 */
@RestController
public class DemoController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AdminService adminService;

    @GetMapping("/hello")
    public String helloSecurity() {
        return "hello-security";
    }

    @GetMapping("/testMybatis")
    public Admin testMybatis() {
        int id = 1;
        Admin admin = adminService.queryById(id);
        return admin;
    }

    @GetMapping("/helloRedis")
    public List<Admin> helloRedis() {
        List<Admin> list = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            Admin admin = new Admin();
            admin.setStatus(i);
            admin.setAdminName("test");
            admin.setEmail("test@qq.com");
            list.add(admin);
            redisTemplate.opsForValue().set("admin" + i, admin);
        }
        adminService.saveBatch(list);
        return list;
    }
}
