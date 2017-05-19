package com.example.badgelibrary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tony on 5/17/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "badge.db";
    private static final int DB_VERSION = 1;
    public static final String TB_BADGE_NAME = "tb_badge";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private String getCreateSQL(){
        String createString = "CREATE TABLE IF NOT EXISTS " + TB_BADGE_NAME +
                " (_id INTEGER PRIMARY KEY," +
                " type INTEGER DEFAULT 0," +
                " state INTEGER DEFAULT 0," +
                " mode INTEGER DEFAULT 0," +
                " count INTEGER DEFAULT 0," +
                " content TEXT DEFAULT ''," +
                " owner TEXT DEFAULT ''," +
                " leader TEXT DEFAULT ''," +
                " UNIQUE(owner));";
        return createString;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getCreateSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
