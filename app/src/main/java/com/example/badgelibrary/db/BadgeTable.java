package com.example.badgelibrary.db;

import android.provider.BaseColumns;

/**
 * Created by tony on 5/19/17.
 */

public class BadgeTable {
    public static final String TABLE_NAME = "ta_badge";

    //显示状态
    public static final int STATE_NORMAL = 0;//以其他条件决定
    public static final int STATE_VISIBLE = 1;//以该条件决定显示
    public static final int STATE_INVISIBLE = 2;//以该条件决定不显示

    //计算模式,自动/手动
    public static final int MODE_MANUAL = 0;//手动设置值
    public static final int MODE_AUTO = 1;//自动根据依赖计算计数

    public static class Columns implements BaseColumns {
        public static final String BADGE_USER = "user";//支持多用户
        public static final String BADGE_NAME = "name";
        public static final String BADGE_PARENT = "parent";
        public static final String BADGE_STATE = "state";
        public static final String BADGE_MODE = "mode";
        public static final String BADGE_COUNT = "count";
        public static final String BADGE_CONTENT = "content";
    }

    public static String getCreateSQL() {
        String createString = TABLE_NAME + " ( _id INTEGER PRIMARY KEY ,"
                + Columns.BADGE_USER + " TEXT DEFAULT '' ,"
                + Columns.BADGE_NAME + " TEXT NOT NULL UNIQUE, "
                + Columns.BADGE_PARENT + " TEXT DEFAULT '' ,"
                + Columns.BADGE_STATE + " INTEGER DEFAULT 0 ,"
                + Columns.BADGE_MODE + " INTEGER DEFAULT 0 ,"
                + Columns.BADGE_COUNT + " INTEGER DEFAULT 0 ,"
                + Columns.BADGE_CONTENT + " TEXT DEFAULT '' "
                + ");";
        return "CREATE TABLE IF NOT EXISTS " + createString;
    }
}
