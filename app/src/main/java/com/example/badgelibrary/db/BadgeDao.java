package com.example.badgelibrary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.badgelibrary.Badge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tony on 5/17/17.
 */

public class BadgeDao extends Dao {
    private static final String TAG = BadgeDao.class.getSimpleName();

    public BadgeDao(Context context) {
        super(context);
    }

    @Override
    public void insert(Badge badge) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BadgeTable.Columns.BADGE_USER, badge.getUser());
        contentValues.put(BadgeTable.Columns.BADGE_NAME, badge.getName());
        contentValues.put(BadgeTable.Columns.BADGE_PARENT, badge.getParent());
        contentValues.put(BadgeTable.Columns.BADGE_STATE, badge.getState());
        contentValues.put(BadgeTable.Columns.BADGE_MODE, badge.getMode());
        contentValues.put(BadgeTable.Columns.BADGE_COUNT, badge.getContent());
        contentValues.put(BadgeTable.Columns.BADGE_CONTENT, badge.getContent());
        db.insert(BadgeTable.TABLE_NAME, null, contentValues);
        Log.i(TAG, "insert:" + badge.toString());
        db.close();
    }

    @Override
    public void delete(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(BadgeTable.TABLE_NAME, BadgeTable.Columns.BADGE_NAME + "=?", new String[]{name});
        Log.i(TAG, "delete:" + name);
        db.close();
    }

    @Override
    public void update(Badge badge) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BadgeTable.Columns.BADGE_USER, badge.getUser());
        contentValues.put(BadgeTable.Columns.BADGE_NAME, badge.getName());
        contentValues.put(BadgeTable.Columns.BADGE_PARENT, badge.getParent());
        contentValues.put(BadgeTable.Columns.BADGE_STATE, badge.getState());
        contentValues.put(BadgeTable.Columns.BADGE_MODE, badge.getMode());
        contentValues.put(BadgeTable.Columns.BADGE_COUNT, badge.getContent());
        contentValues.put(BadgeTable.Columns.BADGE_CONTENT, badge.getContent());
        db.update(BadgeTable.TABLE_NAME, contentValues, BadgeTable.Columns.BADGE_NAME + "=?", new String[]{badge.getName()});
        Log.i(TAG, "update:" + badge.toString());
        db.close();
    }

    @Override
    public Badge query(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(BadgeTable.TABLE_NAME, null, BadgeTable.Columns.BADGE_NAME + "=?", new String[]{name}, null, null, null);
            if (cursor.moveToFirst()) {
                Badge badge = new Badge(name);
                // TODO: 2018/2/3 多用户啊
                badge.setUser(cursor.getString(cursor.getColumnIndex(BadgeTable.Columns.BADGE_USER)));
                badge.setParent(cursor.getString(cursor.getColumnIndex(BadgeTable.Columns.BADGE_PARENT)));
                badge.setState(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_STATE)));
                badge.setMode(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_MODE)));
                badge.setCount(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_COUNT)));
                badge.setContent(cursor.getString(cursor.getColumnIndex(BadgeTable.Columns.BADGE_CONTENT)));
                Log.i(TAG, "query:" + badge.toString());
                return badge;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    @Override
    public List<Badge> query() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        List<Badge> mBadges = new ArrayList<>();
        try {
            cursor = db.query(BadgeTable.TABLE_NAME, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                // TODO: 2018/2/3 多用户
                Badge badge = new Badge(cursor.getString(cursor.getColumnIndex(BadgeTable.Columns.BADGE_NAME)));
                badge.setUser(cursor.getString(cursor.getColumnIndex(BadgeTable.Columns.BADGE_USER)));
                badge.setParent(cursor.getString(cursor.getColumnIndex(BadgeTable.Columns.BADGE_PARENT)));
                badge.setState(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_STATE)));
                badge.setMode(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_MODE)));
                badge.setCount(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_COUNT)));
                badge.setContent(cursor.getString(cursor.getColumnIndex(BadgeTable.Columns.BADGE_CONTENT)));
                mBadges.add(badge);
                Log.i(TAG, "query:" + badge.toString());
            }
        } catch (Exception e) {
            Log.e(TAG, "query:" + e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return mBadges;
    }
}
