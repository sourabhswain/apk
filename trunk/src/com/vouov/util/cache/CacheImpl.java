package com.vouov.util.cache;

import android.content.Context;
import com.vouov.util.security.EncryptUtils;

import java.io.File;
import java.util.Date;

/**
 * User: yuminglong
 * Date: 11-8-9
 * Time: 上午12:16
 * Version: 1.0.0
 */
public class CacheImpl implements ICache<byte[]> {
    /**
     * 默认4M缓存
     */
    private long cacheSize = 4 * 1024 * 1024;

    private File cacheDIR;

    public CacheImpl(Context context){
        this.cacheDIR = context.getCacheDir();
    }

    /**
     * 加入缓存
     *
     * @param key
     * @param value
     * @param time
     * @throws Exception
     */
    public void put(String key, byte[] value, long time) throws Exception {
        long nTime = new Date().getTime();
        String md5Key = EncryptUtils.md5_16(key);
        //查找重复的记录，如果有更新，没有则插入

    }

    /**
     * 加入缓存
     *
     * @param key
     * @param value
     */
    public void put(String key, byte[] value) throws Exception {
        put(key, value, cacheSize);
    }

    /**
     * 获得缓存中的对象,没有默认返回设置的默认值
     *
     * @param key
     * @param defaultVal
     * @return
     */
    public byte[] get(String key, byte[] defaultVal) throws Exception {
        byte[] bytes = get(key);
        if(bytes==null){
            return defaultVal;
        }else {
            return bytes;
        }
    }


    /**
     * 获得缓存中的对象,没有默认返回null
     *
     * @param key
     * @return
     */
    public byte[] get(String key) throws Exception {
        long nTime = new Date().getTime();
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 设置缓存的大小
     *
     * @param size
     */
    public void setCacheSize(long size) {
        this.cacheSize = size;
    }

    /**
     * 获取缓存的大小设置
     *
     * @return
     */
    public long getCacheSize() {
        return this.cacheSize;
    }

    /**
     * 获得缓存目前的使用大小
     *
     * @return
     */
    public long size() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 删除缓存中某个值
     *
     * @param key
     * @return
     */
    public void remove(String key) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 清除缓存
     *
     * @return
     */
    public long clear() {
        long nTime = new Date().getTime();
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 刷新缓存
     *
     * @return
     */
    public long refresh() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
