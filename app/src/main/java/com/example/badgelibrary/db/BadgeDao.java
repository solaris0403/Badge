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
        contentValues.put(BadgeTable.Columns.BADGE_TYPE, badge.getDisplayType());
        contentValues.put(BadgeTable.Columns.BADGE_STATE, badge.getDisplayState());
        contentValues.put(BadgeTable.Columns.BADGE_MODE, badge.getDisplayMode());
        contentValues.put(BadgeTable.Columns.BADGE_COUNT, badge.getCount());
        contentValues.put(BadgeTable.Columns.BADGE_CONTENT, badge.getContent());
        contentValues.put(BadgeTable.Columns.BADGE_OWNER, badge.getOwner());
        contentValues.put(BadgeTable.Columns.BADGE_LEADER, badge.getLeader());
        db.insert(BadgeTable.TABLE_NAME, null, contentValues);
        Log.d(TAG, "insert:" + badge.toString());
        db.close();
    }

    @Override
    public void delete(String owner) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(BadgeTable.TABLE_NAME, BadgeTable.Columns.BADGE_OWNER + "=?", new String[]{owner});
        Log.d(TAG, "delete:" + owner);
        db.close();
    }

    @Override
    public void update(Badge badge) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BadgeTable.Columns.BADGE_TYPE, badge.getDisplayType());
        contentValues.put(BadgeTable.Columns.BADGE_STATE, badge.getDisplayState());
        contentValues.put(BadgeTable.Columns.BADGE_MODE, badge.getDisplayMode());
        contentValues.put(BadgeTable.Columns.BADGE_COUNT, badge.getCount());
        contentValues.put(BadgeTable.Columns.BADGE_CONTENT, badge.getContent());
        contentValues.put(BadgeTable.Columns.BADGE_OWNER, badge.getOwner());
        contentValues.put(BadgeTable.Columns.BADGE_LEADER, badge.getLeader());
        db.update(BadgeTable.TABLE_NAME, contentValues, BadgeTable.Columns.BADGE_OWNER + "=?", new String[]{badge.getOwner()});
        Log.d(TAG, "update:" + badge.toString());
        db.close();
    }

    @Override
    public Badge query(String owner) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(BadgeTable.TABLE_NAME, null, BadgeTable.Columns.BADGE_OWNER + "=?", new String[]{owner}, null, null, null);
            if (cursor.moveToFirst()) {
                Badge badge = new Badge(owner);
                badge.setDisplayType(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_TYPE)));
                badge.setDisplayState(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_STATE)));
                badge.setDisplayMode(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_MODE)));
                badge.setCount(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_COUNT)));
                badge.setContent(cursor.getString(cursor.getColumnIndex(BadgeTable.Columns.BADGE_CONTENT)));
                badge.setLeader(cursor.getString(cursor.getColumnIndex(BadgeTable.Columns.BADGE_LEADER)));
                Log.d(TAG, "query:" + badge.toString());
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
                Badge badge = new Badge(cursor.getString(cursor.getColumnIndex(BadgeTable.Columns.BADGE_OWNER)));
                badge.setDisplayType(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_TYPE)));
                badge.setDisplayState(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_STATE)));
                badge.setDisplayMode(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_MODE)));
                badge.setCount(cursor.getInt(cursor.getColumnIndex(BadgeTable.Columns.BADGE_COUNT)));
                badge.setContent(cursor.getString(cursor.getColumnIndex(BadgeTable.Columns.BADGE_CONTENT)));
                badge.setLeader(cursor.getString(cursor.getColumnIndex(BadgeTable.Columns.BADGE_LEADER)));
                mBadges.add(badge);
                Log.d(TAG, "query:" + badge.toString());
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
