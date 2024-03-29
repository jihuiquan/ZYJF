package com.dynamic.foundations.sqlite;

import android.database.Cursor;
import com.dynamic.foundations.core.lang.Closure;
import com.dynamic.foundations.core.lang.Predicate;
import com.dynamic.foundations.core.lang.Transformer;

/**
 * Template for cursor
 *
 * @author snowway
 * @since 3/20/11
 */
public class CursorTemplate {
    /**
     * 遍历是否游标有任何一个匹配
     *
     * @param cursor      Cursor
     * @param predicate   Predicate
     * @param closeCursor 是否关闭游标
     * @return 是否有任何一个匹配
     */
    public static boolean any(Cursor cursor, Predicate<Cursor> predicate, boolean closeCursor) {
        if (cursor == null) {
            return false;
        }
        try {
            if (cursor.moveToFirst()) {
                do {
                    boolean result = predicate.eval(cursor);
                    if (result) {
                        return true;
                    }

                } while (cursor.moveToNext());
                return false;
            }
            return false;
        } finally {
            if (closeCursor && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    /**
     * 遍历是否游标有任何一个匹配
     *
     * @param cursor    Cursor
     * @param predicate Predicate
     * @return 是否有任何一个匹配
     */
    public static boolean any(Cursor cursor, Predicate<Cursor> predicate) {
        return any(cursor, predicate, true);
    }

    /**
     * 遍历cursor
     *
     * @param cursor      Android Cursor
     * @param closure     Closure
     * @param closeCursor close cursor?
     */
    public static void each(Cursor cursor, Closure<Cursor> closure, boolean closeCursor) {
        if (cursor == null) {
            return;
        }
        try {
            if (cursor.moveToFirst()) {
                do {
                    try {
                        closure.execute(cursor);
                    } catch (Closure.VetoException ex) {
                        break;
                    }
                } while (cursor.moveToNext());
            }
        } finally {
            if (closeCursor && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    public static void each(Cursor cursor, CursorCallback callback, boolean closeCursor) {
        if (cursor == null) {
            throw new IllegalArgumentException("cursor is null");

        }
        if (cursor.getCount() == 0) {
            if (closeCursor && !cursor.isClosed()) {
                cursor.close();
            }
            return;
        }
        try {
            if (cursor.moveToFirst()) {
                do {
                    Object result = callback.doInCursor(cursor);
                    if (result == CursorCallback.VETO) {
                        break;
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (closeCursor && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    public static void one(Cursor cursor, Closure<Cursor> closure, boolean closeCursor) {
        if (cursor == null) {
            return;
        }
        try {
            if (cursor.moveToNext()) {
                closure.execute(cursor);
            }
        } finally {
            if (closeCursor && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    public static void one(Cursor cursor, Closure<Cursor> closure) {
        one(cursor, closure, true);
    }


    public static void each(Cursor cursor, Closure<Cursor> closure) {
        each(cursor, closure, true);
    }

    public static void each(Cursor cursor, CursorCallback callback) {
        each(cursor, callback, true);
    }


    /**
     * 从cursor中读取一条记录
     *
     * @param cursor      Android Cursor
     * @param transformer Transformer
     * @param <T>         return type
     * @return return value
     */
    public static <T> T one(Cursor cursor, Transformer<Cursor, T> transformer) {
        return one(cursor, transformer, true);
    }

    /**
     * 从cursor中读取一条记录
     *
     * @param cursor      Android Cursor
     * @param transformer Transformer
     * @param closeCursor close cursor after select
     * @param <T>         return type
     * @return return value
     */
    public static <T> T one(Cursor cursor, Transformer<Cursor, T> transformer, boolean closeCursor) {
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToNext()) {
                return transformer.transform(cursor);
            } else {
                return null;
            }
        } finally {
            if (closeCursor && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }


    /**
     * 游标是否存在数据
     *
     * @param cursor Cursor
     * @return exists
     */
    public static boolean exist(Cursor cursor) {
        if (cursor == null) {
            return false;
        }
        boolean exist;
        try {
            exist = cursor.getCount() > 0;
        } finally {
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return exist;
    }
}
