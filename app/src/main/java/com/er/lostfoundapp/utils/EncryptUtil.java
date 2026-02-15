package com.er.lostfoundapp.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtil {
    // 盐值（企业级：盐值可从服务端获取，这里固定用于演示）
    private static final String SALT = "campus_helper_2026";

    /**
     * 密码加密：MD5(密码+盐值)
     */
    public static String encryptPassword(String password) {
        String raw = password + SALT;
        return DigestUtils.md5Hex(raw).toUpperCase();
    }

}
