package com.zqx.java.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.zqx.java.springboot.core.BusinessException;
import com.zqx.java.springboot.entity.User;
import com.zqx.java.springboot.service.UserService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@Validated
@RequestMapping("/base")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 用户列表(不分页)
     */
    @RequestMapping(value = "/userList")
    @ResponseBody
    public String getUserList(HttpServletRequest request, Map map) throws Exception {

        String id = request.getSession(true).getId();
        System.out.println(id);

        List<User> userList = userService.getUserList();
        map.put("userList", userList);
        String userListStr = JSON.toJSONString(userList);
        String join = StringUtils.join(new String[]{"A", "B"});
        System.out.println(join);
        return userListStr;
    }

    /**
     * 用户列表(分页)
     * @param pageNum  页值
     * @param pageSize 页大小
     */
    @ExceptionHandler(BusinessException.class)
    @RequestMapping("/userPages")
    @ResponseBody
    public String getUserList(Map map, @Min(1) @RequestParam(required = false) int pageNum, @RequestParam(required = false) int pageSize) throws Exception {

        List<User> userList = userService.getUserList(pageNum, pageSize);
        map.put("userList", userList);
        String userListStr = JSON.toJSONString(userList);
        return userListStr;
    }

    /**
     * 通过用户名查询用户
     */
    @RequestMapping("/user")
    @ResponseBody
    public String getUser(Map map, @RequestParam("name") String name) throws Exception {
        User user = userService.getUserByName(name);
        String userStr = JSON.toJSONString(user);
        return userStr;
    }

    /**
     * 用户插入
     */
    @RequestMapping("/insert")
//    @ResponseBody
    public String insertUser(Map map, User user) throws Exception {
        boolean isOk = userService.insertUser(user);
        if (isOk) {
            return "index";
        }
        return "error";
    }

    /**
     * 文件上传到指定目录【E:/upload/】
     * @param file 上传文件
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String uploadImage(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String path = "E:/upload/";
        File uploadFile = new File(path + filename);
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "index";
    }

}
