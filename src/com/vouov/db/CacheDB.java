package com.vouov.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.vouov.cache.Cache;
import com.vouov.exception.SVException;
import com.vouov.util.DBUtils;

import java.util.HashSet;
import java.util.List;

/**
 * User: yuminglong
 * Date: 11-8-9
 * Time: 上午12:56
 * Version: 1.0.0
 */
public class CacheDB extends SQLiteOpenHelper implements RowHandler<Cache>{
    private final static String TAG = "CacheDB";

    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "vouov";
    private final static String TABLE_NAME = "cache";
    private final static String CACHE_ID = "_id";
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

    /**
     * 根据key获取记录
     *
     * @param key
     * @return
     */
    public Cache getByKey(String key) throws SVException {
        Cache cache = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, CACHE_KEY + "=?", new String[]{key}, null, null, null);
        cache = DBUtils.selectOne(this, cursor);
        cursor.close();
        return cache;
    }

    public long addCache(Cache cache) throws SVException {
        long newId = 0;
        if(cache!=null){
            Cache cache1 = null;
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query(TABLE_NAME, null, CACHE_KEY + "=?", new String[]{cache.getKey()}, null, null, null);
            cache1 = DBUtils.selectOne(this, cursor);
           /* if(){
                db.insert(TABLE_NAME, )
            }*/
        }
        return newId;
    }

    public List<Cache> getExpireCaches(){
        return  null;
    }

    public HashSet<String> cleanExpireCaches(){
        return null;
    }

    public Cache rowProcess(Cursor cursor) {
        Cache cache = new Cache();
        cache.setId(DBUtils.getLong(cursor, CACHE_ID));
        cache.setKey(DBUtils.getString(cursor, CACHE_KEY));
        cache.setFileName(DBUtils.getString(cursor, CACHE_FILE_NAME));
        cache.setLastModified(DBUtils.getLong(cursor, CACHE_LAST_MODIFIED));
        cache.setExpire(DBUtils.getLong(cursor, CACHE_EXPIRE));
        cache.setSize(DBUtils.getLong(cursor, CACHE_EXPIRE));
        cache.setCount(DBUtils.getInt(cursor, CACHE_COUNT));
        return cache;
    }
}
