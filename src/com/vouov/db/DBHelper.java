package com.vouov.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * User: yuminglong
 * Date: 11-9-2
 * Time: 上午11:26
 * Version: 1.0.0
 */
public abstract class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 查询表记录数
     * @param table
     * @param selection
     * @param selectionArgs
     * @return
     */
    public long getCount(String table, String selection, String[] selectionArgs) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT COUNT(*) FROM `" + table + "`";
        if (selection != null && !"".equals(selection)) {
            sql += selection;
        }
        SQLiteStatement statement = db.compileStatement(sql);
        if (selectionArgs != null && selectionArgs.length > 0) {
            int lenght = selectionArgs.length;
            for (int i = 0; i < lenght; i++) {
                if (selectionArgs[i] == null) {
                    statement.bindNull(i);
                } else {
                    statement.bindString(i, selectionArgs[i]);
                }
            }
        }
        long count = statement.simpleQueryForLong();
        statement.close();
        return count;
    }
}
