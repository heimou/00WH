package com.wh.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 加密工具
 * @author:liyujie
 * @date:2018/6/19
 */
public class CryptographyUtil {

    private final static Logger logger = LoggerFactory.getLogger(CryptographyUtil.class);

    /**
     * base 64加密
     *
     * @param str
     * @return
     */
    public static String encBase64(String str) {
        return Base64.encodeToString(str.getBytes());
    }

    /**
     * base 64 解密
     *
     * @param str
     * @return
     */
    public static String decBase64(String str) {
        return Base64.decodeToString(str);
    }

    /**
     * Md5Hash 加密
     *
     * @param password 密码
     * @param salt     盐 即额外拼接一起加密
     * @return
     */
    public static String md5(String password, String salt) {
        return new Md5Hash(password, salt).toString();
    }

    /**
     * Md5Hash 加密
     *
     * @param password       需要加密的密码
     * @param salt           盐 即额外拼接一起加密
     * @param hashIterations 加密的次数
     * @return
     */
    public static String md5(String password, String salt, Integer hashIterations) {
        return new Md5Hash(password, salt, hashIterations).toString();
    }
}
