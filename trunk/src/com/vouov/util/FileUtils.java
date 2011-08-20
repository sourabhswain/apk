package com.vouov.util;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: yuml
 * Date: 11-8-7
 * Time: 上午10:14
 * To change this template use File | Settings | File Templates.
 */
public class FileUtils {
    /**
     * 判断文件是否过期
     *
     * @param fileName
     * @param dir
     * @param expire
     * @return
     */
    public static boolean isExpire(String fileName, String dir, long expire) {
        boolean rs = true;
        long now = new Date().getTime();
        File directory = new File(dir);
        File file = new File(directory, fileName);
        return isExpire(file, expire);
    }

    /**
     * 判断文件是否过期
     *
     * @param file
     * @param expire
     * @return
     */
    public static boolean isExpire(File file, long expire) {
        boolean rs = true;
        long now = new Date().getTime();
        if (file.isFile()) {
            long mtime = file.lastModified();
            if ((now - mtime) > expire) {
                rs = true;
            } else {
                rs = false;
            }
        }
        return rs;
    }

    /**
     * 获取目录下的文件按照时间排序
     * @param dir
     * @param isAsc  是否升序
     * @return
     */
    public static File[] getFilesOrderByModified(String dir, final boolean isAsc) {
        File[] files = null;
        File directory = new File(dir);
        if (directory.exists() && directory.isDirectory()) {
            files = directory.listFiles();
            Arrays.sort(files, new Comparator<File>() {
                public int compare(File file, File file1) {
                    int rs = 0;
                    long diffTime = file.lastModified() - file1.lastModified();
                    if (diffTime > 0) {
                        rs = 1;
                    } else if (diffTime == 0) {
                        rs = 0;
                    } else {
                        rs = -1;
                    }
                    if (!isAsc) {
                        rs = rs * -1;
                    }
                    return rs;
                }
            });
        }
        return files;
    }

    /**
     * 删除过期的文件
     *
     * @param dir
     * @param expire
     * @return
     */
    public static long deleteExpireFile(String dir, long expire) {
        long size = 0;
        File directory = new File(dir);
        if (directory.exists()) {
            File[] list = directory.listFiles();
            if (list != null && list.length > 0) {
                long now = new Date().getTime();
                for (File file : list) {
                    if (file.isFile()) {
                        long mtime = file.lastModified();
                        if ((now - mtime) > expire) {
                            if (file.delete())
                                size++;
                        }
                    }
                }
            }
        }
        return size;
    }

    /**
     * 保存字符串进文件
     *
     * @param text
     * @param fileName
     * @param dir
     * @return
     */
    public static boolean saveFile(String text, String fileName, String dir) {
        boolean rs = false;
        File directory = new File(dir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(dir, fileName);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(text);
            fw.close();
            rs = true;
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        return rs;
    }

    /**
     * 保存字节数组进文件
     *
     * @param content
     * @param fileName
     * @param dir
     * @return
     */
    public static boolean saveFile(byte[] content, String fileName, String dir) {
        boolean rs = false;
        File directory = new File(dir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(dir, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(content);
            fos.close();
            rs = true;
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        return rs;
    }
}
