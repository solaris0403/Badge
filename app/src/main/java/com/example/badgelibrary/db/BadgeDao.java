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
    private static final String TAG = "BadgeDao";

    public BadgeDao(Context context) {
        super(context);
    }

    @Override
    public void insert(Badge badge) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", badge.getDisplayType());
        contentValues.put("state", badge.getDisplayState());
        contentValues.put("mode", badge.getDisplayMode());
        contentValues.put("count", badge.getCount());
        contentValues.put("content", badge.getContent());
        contentValues.put("owner", badge.getOwner());
        contentValues.put("leader", badge.getLeader());
        db.insert(DatabaseHelper.TB_BADGE_NAME, null, contentValues);
        Log.d(TAG, "insert:" + badge.toString());
        db.close();
    }

    @Override
    public void delete(String owner) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TB_BADGE_NAME, "owner = ?", new String[]{owner});
        Log.d(TAG, "delete:" + owner);
        db.close();
    }

    @Override
    public void update(Badge badge) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", badge.getDisplayType());
        contentValues.put("state", badge.getDisplayState());
        contentValues.put("mode", badge.getDisplayMode());
        contentValues.put("count", badge.getCount());
        contentValues.put("content", badge.getContent());
        contentValues.put("owner", badge.getOwner());
        contentValues.put("leader", badge.getLeader());
        db.update(DatabaseHelper.TB_BADGE_NAME, contentValues, "owner = ?", new String[]{badge.getOwner()});
        Log.d(TAG, "updateInfo:" + badge.toString());
        db.close();
    }

    @Override
    public Badge query(String owner) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(DatabaseHelper.TB_BADGE_NAME, null, "owner = ?", new String[]{owner}, null, null, null);
            if (cursor.moveToFirst()) {
                Badge badge = new Badge(owner);
                badge.setDisplayType(cursor.getInt(cursor.getColumnIndex("type")));
                badge.setDisplayState(cursor.getInt(cursor.getColumnIndex("state")));
                badge.setDisplayMode(cursor.getInt(cursor.getColumnIndex("mode")));
                badge.setCount(cursor.getInt(cursor.getColumnIndex("count")));
                badge.setContent(cursor.getString(cursor.getColumnIndex("content")));
                badge.setLeader(cursor.getString(cursor.getColumnIndex("leader")));
                Log.d(TAG, "queryInfo:" + badge.toString());
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
            cursor = db.query(DatabaseHelper.TB_BADGE_NAME, null, null, null, null, null, null);
            while (cursor.moveToNext()){
                Badge badge = new Badge(cursor.getString(cursor.getColumnIndex("owner")));
                badge.setDisplayType(cursor.getInt(cursor.getColumnIndex("type")));
                badge.setDisplayState(cursor.getInt(cursor.getColumnIndex("state")));
                badge.setDisplayMode(cursor.getInt(cursor.getColumnIndex("mode")));
                badge.setCount(cursor.getInt(cursor.getColumnIndex("count")));
                badge.setContent(cursor.getString(cursor.getColumnIndex("content")));
                badge.setLeader(cursor.getString(cursor.getColumnIndex("leader")));
                mBadges.add(badge);
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
        return mBadges;
    }
}
