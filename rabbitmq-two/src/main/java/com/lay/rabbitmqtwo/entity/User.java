package com.lay.rabbitmqtwo.entity;

import java.io.Serializable;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 19:17 2018/12/18
 * @Modified By:IntelliJ IDEA
 */
public class User implements Serializable {
    private static final long serialVersionUID = 426370694438321946L;
    private Long id;
    private String userName;
    private String sex;
    private Integer age;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
