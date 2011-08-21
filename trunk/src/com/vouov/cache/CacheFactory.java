package com.vouov.cache;

import android.graphics.Bitmap;

/**
 * User: yuminglong
 * Date: 11-8-9
 * Time: 上午12:08
 * Version: 1.0.0
 */
public class CacheFactory {
    private static ICache<Bitmap> imageCache;
    private static ICache<String> textCache;

    /**
     * 获取图片缓存
     * @return
     */
    public static ICache<Bitmap> getImageCache() {
        if (imageCache == null) {
            //TODO 获取配置文件中的缓存路径
            imageCache = new ImageFileCache("");
        }
        return imageCache;
    }
}
