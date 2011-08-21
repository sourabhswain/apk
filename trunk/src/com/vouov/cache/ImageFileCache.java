package com.vouov.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.vouov.util.EncryptUtils;
import com.vouov.util.FileUtils;
import com.vouov.util.ImageUtils;

import java.io.File;
import java.util.Date;

/**
 *  图片文件缓存，是对图片文件单独缓存的实现
 * User: yuml
 * Date: 11-8-6
 * Time: 上午9:25
 */
public class ImageFileCache implements ICache<Bitmap> {
    private String cacheDir;
    //默认缓存 3 天
    private long expireTime = 3 * 24 * 3600 * 1000;
    private long cacheSize = 200;
    private String suffix = ".jpg";

    public ImageFileCache(String cacheDir) {
        this.cacheDir = cacheDir;
    }

    /**
     * 加入缓存
     *
     * @param key
     * @param value
     */
    public void put(String key, Bitmap value) throws Exception {
        //判读缓存目录中的文件是否已满，如果已经满了就清除一半，并且清除过期的文件
        File dir = new File(cacheDir);
        if (dir.exists() && dir.isDirectory()) {
            File[] list = FileUtils.getFilesOrderByModified(cacheDir, false);
            if (list != null && list.length >= cacheSize) {
                int halfLength = list.length / 2;
                for (int i = 0; i < list.length; i++) {
                    if (i >= halfLength || FileUtils.isExpire(list[i], expireTime)) {
                        list[i].delete();
                    }
                }
            }
        }
        String md5Key = EncryptUtils.md5(key);
        ImageUtils.saveImage(value, md5Key, suffix, cacheDir);
    }

    /**
     * 加入缓存
     *
     * @param key
     * @param value
     * @param time
     * @throws Exception
     */
    public void put(String key, Bitmap value, long time) throws Exception {
        this.put(key, value);
    }

    /**
     * 获得缓存中的对象,没有默认返回null
     *
     * @param key
     * @return
     */
    public Bitmap get(String key) throws Exception {
        Bitmap bitmap = null;
        String md5Key = EncryptUtils.md5(key);
        File directory = new File(cacheDir);
        File file = new File(directory, md5Key + suffix);
        if (file.exists() && file.isFile()) {
            //如果获取的文件过期则清理缓存
            if (FileUtils.isExpire(file, expireTime)) {
                FileUtils.deleteExpireFile(cacheDir, expireTime);
                bitmap = null;
            } else {
                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                //更新时间戳
                file.setLastModified(new Date().getTime());
            }

        }
        return bitmap;
    }

    /**
     * 获得缓存中的对象,没有默认返回设置的默认值
     *
     * @param key
     * @param defaultVal
     * @return
     */
    public Bitmap get(String key, Bitmap defaultVal) throws Exception {
        Bitmap bitmap = get(key);
        if (bitmap == null) {
            bitmap = defaultVal;
        }
        return bitmap;
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
        long size = 0;
        File directory = new File(cacheDir);
        if (directory.exists() && directory.isDirectory()) {
            File[] list = directory.listFiles();
            if (list != null) {
                size = list.length;
            }
        }
        return size;
    }

    /**
     * 删除缓存中某个值
     *
     * @param key
     * @return
     */
    public void remove(String key) throws Exception {
        String md5Key = EncryptUtils.md5(key);
        File directory = new File(cacheDir);
        File file = new File(directory, md5Key + suffix);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    /**
     * 清空缓存
     *
     * @return
     */
    public long clear() {
        long size = 0;
        File directory = new File(cacheDir);
        if (directory.exists() && directory.isDirectory()) {
            File[] list = directory.listFiles();
            if (list != null) {
                for (File file : list) {
                    file.delete();
                    size++;
                }
            }
        }
        return size;
    }

    /**
     * 刷新缓存
     *
     * @return
     */
    public long refresh() {
        return 0;
    }
}
