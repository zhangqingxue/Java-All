package com.zqx.java.springboot.controller;

import com.zqx.java.springboot.core.NotNullOrEmpty;
import com.zqx.java.springboot.core.Response;
import com.zqx.java.springboot.entity.User;
import com.zqx.java.springboot.service.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.lang.ref.SoftReference;
import java.util.Stack;

/**
 * 用户相关
 */
@RestController
@Validated
@RequestMapping("/api/v1/user")
@Api(value = "user", tags = "用户登录模块")
public class UserRestController {

    @Autowired
//    @Reference(version = "1.0.0")
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Response login(HttpServletRequest request, @NotNullOrEmpty String userName, @NotNull String password) throws Exception {
//        SetOperations opsForSet = redisTemplate.opsForSet();
//        opsForSet.add("userName", userName);
        HttpSession session = request.getSession();
        System.out.println("login");
        User user = userService.getUser(userName, password);
        Response response = new Response(5001, "SERVERERROR");
        if(user != null && StringUtils.isNotEmpty(user.getLoginName())){
            response.setCode(1000);
            response.setDescription("OK");
            response.setResult(user);
        }else{
            response.setCode(1001);
            response.setDescription("ERROR");
        }
        return response;
    }

    /**R
     * 占位符的方式传值
     * 前端传递：api/v1/user/lgoin/ZHANG = /login/{userName}
     */
    @RequestMapping(value = "/login/{userName}", method = RequestMethod.GET)
    public User login(@PathVariable String userName) {
        System.out.println(userName);
        return null;
    }

}
