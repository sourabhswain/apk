package com.vouov.util.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.vouov.util.cache.Cache;

/**
 * User: yuminglong
 * Date: 11-8-9
 * Time: 上午12:56
 * Version: 1.0.0
 */
public class CacheDB extends SQLiteOpenHelper {
    private final static String TAG = "CacheDB";

    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "vouov";
    private final static String TABLE_NAME = "cache";
    private final static String CACHE_ID = "id";
    private final static String CACHE_KEY = "key";
    private final static String CACHE_FILE_NAME = "file_name";
    private final static String CACHE_LAST_MODIFIED = "last_modified";
    private final static String CACHE_EXPIRE = "expire";
    private final static String CACHE_SIZE = "size";
    private final static String CACHE_COUNT = "count";

    private final static String CREATE_SQL = "CREATE TABLE " + TABLE_NAME
            + " (" + CACHE_ID + " INTEGER PRIMARY KEY autoincrement,"
            + CACHE_KEY + " TEXT UNIQUE," + CACHE_FILE_NAME + " TEXT," + CACHE_LAST_MODIFIED + " INTEGER,"
            + CACHE_EXPIRE + " INTEGER," + CACHE_SIZE + " INTEGER," + CACHE_COUNT + " INTEGER);";


    public CacheDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public Cache getByKey(String key) {
        Cache cache = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{CACHE_ID, CACHE_KEY, CACHE_FILE_NAME,
                CACHE_LAST_MODIFIED, CACHE_EXPIRE, CACHE_SIZE, CACHE_COUNT}, null, null, null, null, null);
        if(cursor.getCount()>0){

        }
        cursor.close();
        db.close();
        return cache;
    }
}
