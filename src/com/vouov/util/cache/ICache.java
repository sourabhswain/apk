package com.vouov.util.cache;

/**
 * 缓存接口
 * User: yuml
 * Date: 11-8-6
 * Time: 上午9:16
 */
public interface ICache<T>{
    /**
     * 加入缓存
     * @param key
     * @param value
     */
    public void put(String key, T value) throws Exception;

    /**
     * 加入缓存
     * @param key
     * @param value
     * @param time
     * @throws Exception
     */
    public void put(String key, T value, long time) throws Exception;

    /**
     * 获得缓存中的对象,没有默认返回null
     * @param key
     * @return
     */
    public T get(String key) throws Exception;

    /**
     * 获得缓存中的对象,没有默认返回设置的默认值
     * @param key
     * @param defaultVal
     * @return
     */
    public T get(String key, T defaultVal) throws Exception;

    /**
     * 设置缓存的大小
     * @param size
     */
    public void setCacheSize(long size);

    /**
     * 获取缓存的大小设置
     * @return
     */
    public long getCacheSize();

    /**
     * 获得缓存目前的使用大小
     * @return
     */
    public long size();

    /**
     * 删除缓存中某个值
     * @param key
     * @return
     */
    public void remove(String key) throws Exception;

    /**
     * 清除缓存
     * @return
     */
    public long clear();
     /**
     * 刷新缓存
     * @return
     */
    public long refresh();
}
