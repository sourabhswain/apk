package com.vouov.db;

import android.database.Cursor;

/**
 * User: yuminglong
 * Date: 11-10-4
 * Time: 上午1:53
 * Version: 1.0.0
 */
public interface RowHandler <T>{
    public T rowProcess(Cursor cursor);
}
