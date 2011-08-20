package com.vouov.util;

/**
 * Created by IntelliJ IDEA.
 * User: yuml
 * Date: 11-8-7
 * Time: 上午8:09
 * To change this template use File | Settings | File Templates.
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
}
