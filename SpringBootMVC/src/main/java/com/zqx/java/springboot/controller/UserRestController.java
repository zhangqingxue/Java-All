package com.zqx.java.springboot.controller;

import com.zqx.java.springboot.core.NotNullOrEmpty;
import com.zqx.java.springboot.core.Response;
import com.zqx.java.springboot.entity.User;
import com.zqx.java.springboot.service.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

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
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Response login(HttpServletRequest request, @NotNullOrEmpty String userName, @NotNull String password) throws Exception {

        /*
        String k2 = stringRedisTemplate.opsForValue().get("k2");
        System.out.println(k2);

        String k1 = stringRedisTemplate.opsForValue().get("k1");
        System.out.println(k1);

        String k11 = stringRedisTemplate.opsForValue().get("1");
        System.out.println(k11);

        String uuuuuuuu = stringRedisTemplate.opsForValue().get("uuuuuuuu");
        System.out.println(uuuuuuuu);
        */

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
