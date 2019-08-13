package com.zqx.java.springboot.service;

import com.zqx.java.springboot.entity.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.List;

public interface UserService {

    List<User> getUserList();
    List<User> getUserList(int pageNum, int pageSize);

    User getUserByName(String name);

    boolean insertUser(User user);

    User getUser(String userName, String password);
}
