package com.vouov.util;

/**
 * 字符操作工具类
 * User: yuml
 * Date: 11-8-7
 * Time: 上午8:09
 */
public class StringUtils {
    /**
     * 二进制byte转成十六进制
     *
     * @param bytes
     * @return
     */
    public static String byte2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        if (bytes != null) {
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
        }
        return sb.toString();
    }

   /* *//**
     * 二进制byte转成最短字符串
     *
     * @param bytes
     * @return
     *//*
    public static String byte2Short(byte[] bytes) {
        String base = "abcdefghijklmnopqrstuvwxyz123456";
        //String base = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIGKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        if (bytes != null) {
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
        }
        return sb.toString();
    }*/

    /**
     * 判断字符串是否为空
     * @param text
     * @return
     */
    public static boolean isEmpty(String text) {
        if (text == null || "".equals(text.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static String null2Blank(String value){
        if(value==null){
            return "";
        }
        return value;
    }
}
