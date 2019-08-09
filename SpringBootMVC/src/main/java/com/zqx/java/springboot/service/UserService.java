package com.zqx.java.springboot.service;

import com.zqx.java.springboot.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUserList();
    List<User> getUserList(int pageNum, int pageSize);

    User getUserByName(String name);

    boolean insertUser(User user);

    User getUser(String userName, String password);
}
