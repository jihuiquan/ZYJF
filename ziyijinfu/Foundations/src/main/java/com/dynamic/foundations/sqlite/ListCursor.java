package com.dynamic.foundations.sqlite;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;

import java.util.*;

/**
 * @author snowway
 * @since 2010-6-18
 */
public class ListCursor<E> implements Cursor {

    private List<E> items;

    private int position = 0;

    public void add(int index, E e) {
        items.add(index, e);
    }

    public E get(int index) {
        return items.get(index);
    }

    public List<E> getItems() {
        return items;
    }

    public ListCursor(Collection<E> items) {
        if (items == null) {
            this.items = new ArrayList<E>();
        } else {
            this.items = new ArrayList<E>(items);
        }
    }

    public ListCursor(Collection<E> items, Comparator<E> comparator) {
        if (items == null) {
            this.items = new ArrayList<E>();
        } else {
            this.items = new ArrayList<E>(items);
        }
        Collections.sort(this.items, comparator);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public boolean moveToNext() {
        if (position + 1 == items.size()) { //last one
            return false;
        } else {
            position++;
            return true;
        }
    }

    @Override
    public boolean moveToFirst() {
        position = 0;
        return true;
    }

    @Override
    public boolean moveToLast() {
        position = items.size() - 1;
        return true;
    }

    @Override
    public boolean moveToPosition(int i) {
        if (i >= 0 && i < items.size()) {
            position = i;
            return true;
        }
        return false;
    }

    @Override
    public boolean move(int i) {
        return moveToPosition(i);
    }

    @Override
    public boolean moveToPrevious() {
        if (position == 0) {
            return false;
        } else {
            position--;
            return true;
        }
    }

    @Override
    public boolean isFirst() {
        return position == 0;
    }

    @Override
    public boolean isLast() {
        return position == items.size() - 1;
    }

    @Override
    public boolean isBeforeFirst() {
        return isFirst();
    }

    @Override
    public boolean isAfterLast() {
        return isLast();
    }

    public E getItem() {
        return items.get(position);
    }


    //not implement

    @Override
    public int getColumnIndex(String s) {
        return 0;
    }

    @Override
    public int getColumnIndexOrThrow(String s) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public String getColumnName(int i) {
        return null;
    }

    @Override
    public String[] getColumnNames() {
        return new String[0];
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public byte[] getBlob(int i) {
        return new byte[0];
    }

    @Override
    public String getString(int i) {
        return null;
    }

    @Override
    public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {

    }

    @Override
    public short getShort(int i) {
        return 0;
    }

    @Override
    public int getInt(int i) {
        return 0;
    }

    @Override
    public long getLong(int i) {
        return 0;
    }

    @Override
    public float getFloat(int i) {
        return 0;
    }

    @Override
    public double getDouble(int i) {
        return 0;
    }

    public int getType(int i) {
        return i;
    }

    @Override
    public boolean isNull(int i) {
        return false;
    }

    @Override
    public void deactivate() {

    }

    @Override
    public boolean requery() {
        return false;
    }

    @Override
    public void close() {
        if (items != null) {
            items.clear();
            items = null;
        }
    }

    @Override
    public boolean isClosed() {
        return items == null;
    }

    @Override
    public void registerContentObserver(ContentObserver contentObserver) {

    }

    @Override
    public void unregisterContentObserver(ContentObserver contentObserver) {

    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void setNotificationUri(ContentResolver contentResolver, Uri uri) {

    }

    @Override
    public Uri getNotificationUri() {
        return null;
    }

    @Override
    public boolean getWantsAllOnMoveCalls() {
        return false;
    }

    @Override
    public void setExtras(Bundle extras) {

    }

    @Override
    public Bundle getExtras() {
        return null;
    }

    @Override
    public Bundle respond(Bundle bundle) {
        return null;
    }
}
