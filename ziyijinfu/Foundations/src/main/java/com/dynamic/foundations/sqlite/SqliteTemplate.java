package com.dynamic.foundations.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import com.dynamic.foundations.common.utils.StringUtils;
import com.dynamic.foundations.core.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * template for Sqlite database
 *
 * @author snowway
 * @since 2/24/11
 */
public final class SqliteTemplate {

    public static final String LOG_TAG = SqliteTemplate.class.getName();

    private static final Object _lock = new Object();

    private SQLiteOpenHelper sqliteOpenHelper;

    private SQLiteDatabase database;

    public SqliteTemplate(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqliteOpenHelper = sqLiteOpenHelper;
    }

    public SqliteTemplate(SQLiteDatabase database) {
        this.database = database;
    }

    private void populateSqliteDatabase(boolean readonly) {
        if (sqliteOpenHelper == null) {
            return;
        }
        database = readonly ? sqliteOpenHelper.getReadableDatabase() : sqliteOpenHelper.getWritableDatabase();
        if (!readonly) {
            this.database.setLockingEnabled(true);
        }
    }

    /**
     * 查询数据库返回游标
     *
     * @param sql  sql
     * @param args args
     * @return Cursor
     */
    public Cursor query(final String sql, final String[] args) {
        return execute(true, new SqliteCallback<Cursor>() {
            @Override
            public Cursor doInSqlite(SQLiteDatabase database) {
                return database.rawQuery(sql, args);
            }
        });
    }

    /**
     * reset 数据库
     *
     * @param table          表名
     * @param values         插入的值
     * @param nullColumnHack null字段值
     * @return 插入后产生的id
     */
    public long insert(final String table, final ContentValues values, final String nullColumnHack) {
        return execute(false, new SqliteCallback<Long>() {
            @Override
            public Long doInSqlite(SQLiteDatabase database) {
                return database.insert(table, nullColumnHack, values);
            }
        });
    }


    /**
     * 批量插入数据库
     *
     * @param tableName        表名
     * @param contentValueList 插入的值
     */
    public void bulkInsert(String tableName, List<ContentValues> contentValueList) {
        ContentValues contentValues = contentValueList.get(0);
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ")
                .append(tableName)
                .append("(");
        String fieldStr = "";
        Set<Map.Entry<String, Object>> s = contentValues.valueSet();
        for (Map.Entry<String, Object> value : s) {
            Map.Entry me = (Map.Entry) value;
            String key = me.getKey().toString();
            fieldStr += key + ",";
        }
//        for (String field : contentValues.keySet()) {
//            fieldStr += field + ",";
//        }
        sb.append(fieldStr.substring(0, fieldStr.length() - 1));
        sb.append(") ")
                .append("values(");
        String valueStr = "";
        for (int i = 0; i < contentValues.size(); i++) {
            valueStr += "?,";
        }
        sb.append(valueStr.substring(0, valueStr.length() - 1));
        sb.append(")");
        bulkExecute(true, sb.toString(), contentValueList);
    }

    /**
     * 批量执行数据操作
     *
     * @param isInsert          是否是插入数据库
     * @param sql
     * @param contentValuesList
     */
    private void bulkExecute(final boolean isInsert, final String sql, final List<ContentValues> contentValuesList) {
        execute(false, new SqliteCallback<Object>() {
            @Override
            public Object doInSqlite(SQLiteDatabase database) {
                database.beginTransaction();
                try {
                    SQLiteStatement insert = database.compileStatement(sql);
                    for (ContentValues contentValues : contentValuesList) {
                        int index = 0;
                        Set<Map.Entry<String, Object>> s = contentValues.valueSet();
                        for (Map.Entry<String, Object> value : s) {
                            Map.Entry me = (Map.Entry) value;
                            String field = me.getKey().toString();
                            ++index;
                            insert.bindString(index, StringUtils.trimToEmpty(contentValues.getAsString(field)));
                        }
                        if (!isInsert) {
                            insert.execute();
                        } else {
                            insert.executeInsert();
                        }
                    }
                    database.setTransactionSuccessful();
                } finally {
                    database.endTransaction();
                }
                return null;
            }
        });
    }


    /**
     * 更新数据库
     *
     * @param table  表名
     * @param values 字段值
     * @param where  where条件
     * @param args   where参数
     * @return 更新条数
     */
    public int update(final String table, final ContentValues values, final String where, final String[] args) {
        return execute(false, new SqliteCallback<Integer>() {
            @Override
            public Integer doInSqlite(SQLiteDatabase database) {
                return database.update(table, values, where, args);
            }
        });
    }


    /**
     * 删除数据库数据
     *
     * @param table 表名
     * @param where where条件
     * @param args  where参数
     * @return 删除条数
     */
    public int delete(final String table, final String where, final String[] args) {
        return execute(false, new SqliteCallback<Integer>() {
            @Override
            public Integer doInSqlite(SQLiteDatabase database) {
                return database.delete(table, where, args);
            }
        });
    }

    /**
     * 直接执行语句
     *
     * @param sql
     */
    public void execute(final String sql, final String[] objs) {
        execute(false, new SqliteCallback<Object>() {
            @Override
            public Object doInSqlite(SQLiteDatabase database) {
                try {
                    database.beginTransaction();
                    if (objs == null) {
                        database.execSQL(sql);
                    } else {
                        database.execSQL(sql, objs);
                    }
                    database.setTransactionSuccessful();
                } finally {
                    database.endTransaction();
                }
                return null;
            }
        });
    }

    /**
     * 执行数据库操作
     *
     * @param callback SqliteCallback
     * @param <T>      返回类型
     * @return 执行结果
     */
    public <T> T execute(boolean readonly, SqliteCallback<T> callback) {
        try {
            populateSqliteDatabase(readonly);
            synchronized (_lock) {
                if (database.isOpen()) {
                    database.acquireReference();
                    try {
                        return callback.doInSqlite(database);
                    } catch (Exception ex) {
                        Log.e(LOG_TAG, "error execute database", ex);
                        throw new SqliteException(ex.getMessage(), ex);
                    }
                } else {
                    throw new IllegalStateException("database closed");
                }
            }
        } finally {
            synchronized (_lock) {
                if (database != null) {
                    database.releaseReference();
                }
            }
        }
    }

    public boolean isOpen() {
        return database != null && database.isOpen();
    }


//    public void close() {
//        if (database != null && isOpen()) {
//            database.close();
//            database = null;
//        }
//    }
}
