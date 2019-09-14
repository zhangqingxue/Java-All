package com.zqx.java.springboot.mapper;

import com.zqx.java.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    User getUserByName(String name);

    List<User> findAll();

    boolean insertUser(User user);

    User getUser(String userName, String password);
}
