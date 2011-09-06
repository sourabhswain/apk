package com.vouov.http;

import org.apache.http.NameValuePair;

import java.io.File;

/**
 * 文件键值对
 * User: yuminglong
 * Date: 11-9-5
 * Time: 上午11:18
 * Version: 1.0.0
 */
public class FileNameValuePair implements NameValuePair{
    private String name;
    private File file;
    public FileNameValuePair(String name, File file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.file.getName();
    }
    public File getFile(){
        return this.file;
    }

    @Override
    public String toString() {
        return "FileNameValuePair{" +
                "name='" + name + '\'' +
                ", file=" + file.getAbsolutePath() +
                '}';
    }
}
