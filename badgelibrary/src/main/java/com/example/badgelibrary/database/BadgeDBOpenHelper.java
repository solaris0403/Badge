package com.example.badgelibrary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tony on 5/15/17.
 */

public class BadgeDBOpenHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "badge.db";
    public static final String BADGE_TABLE_NAME = "tb_badge";

    public BadgeDBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + BADGE_TABLE_NAME +
                " (type INTEGER DEFAULT 0," +
                " count INTEGER DEFAULT 0," +
                " display_mode INTEGER DEFAULT 0," +
                " UNIQUE(type));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
