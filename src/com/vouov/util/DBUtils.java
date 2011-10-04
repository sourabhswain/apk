package com.vouov.util;

import android.database.Cursor;
import com.vouov.db.RowHandler;
import com.vouov.exception.SVException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: yuminglong
 * Date: 11-10-4
 * Time: 上午1:52
 * Version: 1.0.0
 */
public class DBUtils {

    public static <T> T selectOne(RowHandler<T> handler, Cursor cursor) throws SVException {
        if (handler == null || cursor == null) {
            throw new SVException("Parameter can not be empty");
        }
        if (cursor.moveToNext()) {
            return handler.rowProcess(cursor);
        }
        return null;
    }

    public static <T> List<T> select(RowHandler<T> handler, Cursor cursor) throws SVException {
        if (handler == null || cursor == null) {
            throw new SVException("Parameter can not be empty");
        }
        List<T> list = new ArrayList<T>();
        while (cursor.moveToNext()) {
            list.add(handler.rowProcess(cursor));
        }
        return list;
    }

    public static long getLong(Cursor cursor, String column) {
        return cursor.getLong(cursor.getColumnIndex(column));
    }

    public static int getInt(Cursor cursor, String column) {
        return cursor.getInt(cursor.getColumnIndex(column));
    }

    public static double getDouble(Cursor cursor, String column) {
        return cursor.getDouble(cursor.getColumnIndex(column));
    }

    public static float getFloat(Cursor cursor, String column) {
        return cursor.getFloat(cursor.getColumnIndex(column));
    }

    public static String getString(Cursor cursor, String column) {
        return cursor.getString(cursor.getColumnIndex(column));
    }

    public static Date getDate(Cursor cursor, String column) {
        String value = getString(cursor, column);
        if (value != null && !"".equals(value.trim())) {
            return DateUtils.parseLong(value);
        }
        return null;
    }
}
