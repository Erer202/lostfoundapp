package com.er.lostfoundapp.entity;

public class User {

    // 核心字段
    private String phone;       // 登录手机号（唯一标识）
    private String password;    // 登录密码（仅注册/密码登录用）
    private boolean isAnonymous;// 是否匿名用户（true=匿名，false=登录用户）



    // 带参构造（登录用户）
    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
        this.isAnonymous = false;
    }

    //（匿名用户）
    public User() {
        this.isAnonymous = true;
    }

    // Getter & Setter（仅保留核心字段，删除campus相关）
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }


}
