package com.lay.scheduling.pojo;

import java.io.Serializable;
import java.sql.SQLOutput;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 11:11 2018/11/19
 * @Modified By:IntelliJ IDEA
 */
public class User implements Serializable ,Cloneable{

    private static final long serialVersionUID = -6903703697701298934L;

    private Long id;
    private String userName;
    private String note;

    public User(Long id,String userName,String note){
        this.id=id;
        this.userName=userName;
        this.note=note;
    }

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
