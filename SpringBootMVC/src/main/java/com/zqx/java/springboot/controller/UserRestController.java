package com.zqx.java.springboot.controller;

import com.zqx.java.springboot.entity.User;
import com.zqx.java.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public User login(String userName, String password) {
        User user = userService.getUser(userName, password);
        return user;
    }

}
