package com.vouov.util;

import com.vouov.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 文本加密解密
 * User: yuml
 * Date: 11-8-6
 * Time: 上午10:24
 */
public class EncryptUtils {
    /**
     * 加密
     *
     * @param text      加密文本
     * @param algorithm 算法
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static byte[] encrypt(String text, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] digest = null;
        if (text != null && !"".equals(text)) {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(text.getBytes("UTF-8"));
            digest = md.digest();
        }
        return digest;
    }

    /**
     * MD5摘要  默认生成32位
     *
     * @param text
     * @return
     */
    public static String md5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return StringUtils.byte2Hex(encrypt(text, "MD5"));
    }

    /**
     * MD5摘要 生成最短字符串
     *
     * @param text
     * @return
     */
    /*public static String md5_short(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return StringUtils.byte2Short(encrypt(text,"MD5"));
    }*/

    /**
     * sha-1加密
     *
     * @param text
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String sha(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return StringUtils.byte2Hex(encrypt(text, "SHA-1"));
    }
}
