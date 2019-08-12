package com.zqx.java.springboot.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.zqx.java.springboot.core.BaseProperty;
import com.zqx.java.springboot.core.SecurityProvider;
import com.zqx.java.springboot.entity.User;
import com.zqx.java.springboot.mapper.UserMapper;
import com.zqx.java.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;
    @Autowired
    private BaseProperty property;

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.findAll();
        return userList;
    }

    @Override
    public List<User> getUserList(@Min(1) int pageNum, int pageSize) {
//        String sql = "SELECT id, login_name, `password`, city, age, sex FROM USER_INFO";
//        List<User> users = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(User.class));
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userDao.findAll();
        return userList;
    }

    @Override
    public User getUserByName(String name) {
        System.out.println(property.toString());
        User user = userDao.getUserByName(name);
        return user;
    }

    public boolean insertUser(User user) {
        String password = user.getPassword();
        try {
            password = (String) SecurityProvider.encodeByMD5(password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        user.setPassword(password);
        boolean isOk = userDao.insertUser(user);
        return isOk;

    }

    @Override
    public User getUser(String userName, String password) {
        userDao.getUser(userName, password);
        return null;
    }

}
