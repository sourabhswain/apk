package com.vouov.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * GPS操作类
 * User: yuminglong
 * Date: 11-8-21
 * Time: 上午9:55
 * Version: 1.0.0
 */
public class GPSUtils {
    /**
     * 如果GPS打开则关闭，如果关闭则打开
     * @param context
     */
    public void toggleGPS(Context context) {
        Intent gpsIntent = new Intent();
        gpsIntent.setClassName("com.android.settings",
                "com.android.settings.widget.SettingsAppWidgetProvider");
        gpsIntent.addCategory("android.intent.category.ALTERNATIVE");
        gpsIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, gpsIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }

    }
}
