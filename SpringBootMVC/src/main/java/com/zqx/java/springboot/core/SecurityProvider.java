package com.zqx.java.springboot.core;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityProvider {

    /**
     * MD5加解密
     */
    public static Object encodeByMD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] bytes = digest.digest(text.getBytes("UTF-8"));
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String str = base64Encoder.encode(bytes);
        System.out.println(str);
        return str;
    }

}
