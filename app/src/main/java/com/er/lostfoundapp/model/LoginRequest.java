package com.er.lostfoundapp.model;

public class LoginRequest {

    private String account;    // 账号
    private String password;   // 加密后的密码

    public LoginRequest(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
