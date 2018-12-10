package com.liwh.controller;

import com.liwh.dao.model.Admin;
import com.liwh.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(description = "用户相关接口")
public class DemoController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AdminService adminService;

    @GetMapping("/hello")
    @ApiOperation(value = "第一个测试")
    @ApiImplicitParam(name = "id", value = "测试id", required = true, dataType = "Integer")
    public String helloSecurity(Integer id) {
        return "hello-security";
    }

    @GetMapping("/testMybatis")
    @ApiOperation(value = "测试Mybatis")
    @ApiImplicitParam(name = "admin", value = "测试用户", required = true, dataType = "Long")
    public Admin testMybatis(Admin admin) {
        Admin result = adminService.queryById(admin.getId());
        return result;
    }

    @GetMapping("/helloRedis")
    @ApiOperation(value = "测试Redis")
    @ApiImplicitParam(name = "num", value = "批量的次数", required = false, dataType = "int")
    public List<Admin> helloRedis(Integer num) {
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
