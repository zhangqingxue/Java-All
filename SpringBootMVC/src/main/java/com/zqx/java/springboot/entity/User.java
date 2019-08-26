package com.zqx.java.springboot.entity;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 用户基本信息
 */
//@Validated
public class User {

    private int id;
    private String loginName;
    private String password;
    private String city;

    @Min(value = 18,message = "未成年禁止入内")
    private int age;
    private int sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

}
