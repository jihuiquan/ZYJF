package com.dynamic.foundations.sqlite;

import android.database.Cursor;

/**
 * @author snowway
 * @since 2010-4-22
 */
public interface CursorCallback {

    Object VETO = new Object();

    Object doInCursor(Cursor cursor);
}
