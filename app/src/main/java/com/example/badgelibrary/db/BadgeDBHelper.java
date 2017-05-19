package com.example.badgelibrary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tony on 5/17/17.
 */

public class BadgeDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "badge.db";
    private static final int DB_VERSION = 1;

    public BadgeDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BadgeTable.getCreateSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
