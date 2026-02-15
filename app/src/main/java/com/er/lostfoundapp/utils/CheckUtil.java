package com.er.lostfoundapp.utils;

import java.util.regex.Pattern;

public class CheckUtil {

    // 账号规则：6-16位，仅字母+数字
    private static final Pattern ACCOUNT_PATTERN = Pattern.compile("^[a-zA-Z0-9]{6,16}$");
    // 密码规则：8位以上，含字母+数字
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d).{8,}$");

    /**
     * 校验账号格式
     */
    public static boolean checkAccount(String account) {
        return ACCOUNT_PATTERN.matcher(account).matches();
    }

    /**
     * 校验密码格式
     */
    public static boolean checkPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

}
