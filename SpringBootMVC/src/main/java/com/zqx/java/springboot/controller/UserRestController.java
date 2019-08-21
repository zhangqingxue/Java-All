package com.zqx.java.springboot.controller;

import com.zqx.java.springboot.entity.User;
import com.zqx.java.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping("/api/v1/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public User login(@NotNull(message = "用户名不能为空") String userName, @NotNull String password) {
        User user = userService.getUser(userName, password);
        return user;
    }

    /**R
     * 占位符的方式传值
     * 前端传递：api/v1/user/lgoin/ZHANG = /login/{userName}
     */
    @RequestMapping("/login/{userName}")
    public User login(@PathVariable String userName) {
        System.out.println(userName);
        return null;
    }

}
