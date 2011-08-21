package com.vouov.util;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片操作工具类
 * User: yuml
 * Date: 11-8-7
 * Time: 下午12:45
 */
public class ImageUtils {
    /**
     * 保存图片
     * @param bitmap
     * @param fileName
     * @param subffix
     * @param dir
     * @return
     */
    public static boolean saveImage(Bitmap bitmap, String fileName, String subffix, String dir) {
        boolean rs = false;
        if (bitmap != null) {
            File directory = new File(dir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileOutputStream fos = null;
            try {
                Bitmap.CompressFormat format = null;
                if (".png".equalsIgnoreCase(subffix)) {
                    format = Bitmap.CompressFormat.PNG;
                } else if (".jpg".equalsIgnoreCase(subffix)) {
                    format = Bitmap.CompressFormat.JPEG;
                } else {
                    throw new Exception("不支持此后缀名图片的保存");
                }

                if (format != null) {
                    File file = new File(directory, fileName + subffix);
                    fos = new FileOutputStream(file);
                    bitmap.compress(format, 85, fos);
                    fos.flush();
                    fos.close();
                    rs = true;
                }
            } catch (IOException e) {
                rs = false;
                e.printStackTrace();
            } catch (Exception e) {
                rs = false;
                e.printStackTrace();
            }

        }
        return rs;
    }
}
