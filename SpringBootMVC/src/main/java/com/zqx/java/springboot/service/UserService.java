package com.zqx.java.springboot.service;

import com.zqx.java.springboot.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getUserList() throws SQLException;
    List<User> getUserList(int pageNum, int pageSize) throws SQLException;

    User getUserByName(String name) throws SQLException;

    boolean insertUser(User user) throws SQLException;

    User getUser(String userName, String password) throws SQLException;
}
