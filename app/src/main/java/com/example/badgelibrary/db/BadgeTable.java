package com.example.badgelibrary.db;

import android.provider.BaseColumns;

/**
 * Created by tony on 5/19/17.
 */

public class BadgeTable {
    public static final String TABLE_NAME = "ta_badge";
    //显示形式
    public static final int TYPE_NUMBER = 0;//数字
    public static final int TYPE_DOT = 1;//小红点
    public static final int TYPE_NEW = 2;//new标签
    //显示状态
    public static final int STATE_VISIBLE = 0;//可见
    public static final int STATE_INVISIBLE = 1;//不可见
    //显示模式,自动/手动
    public static final int MODE_AUTO = 0;//自动根据依赖计算计数
    public static final int MODE_MANUAL = 1;//手动

    public static class Columns implements BaseColumns {
        public static final String BADGE_TYPE = "type";
        public static final String BADGE_STATE = "state";
        public static final String BADGE_MODE = "mode";
        public static final String BADGE_COUNT = "count";
        public static final String BADGE_OWNER = "owner";
        public static final String BADGE_LEADER = "leader";
        public static final String BADGE_CONTENT = "content";
    }

    public static String getCreateSQL() {
        String createString = TABLE_NAME + " ( _id INTEGER PRIMARY KEY ,"
                + Columns.BADGE_TYPE + " INTEGER DEFAULT 0 ,"
                + Columns.BADGE_STATE + " INTEGER DEFAULT 0 ,"
                + Columns.BADGE_MODE + " INTEGER DEFAULT 0 ,"
                + Columns.BADGE_COUNT + " INTEGER DEFAULT 0 ,"
                + Columns.BADGE_OWNER + " TEXT NOT NULL UNIQUE, "
                + Columns.BADGE_LEADER + " TEXT DEFAULT '' ,"
                + Columns.BADGE_CONTENT + " TEXT DEFAULT '' "
                + ");";
        return "CREATE TABLE IF NOT EXISTS " + createString;
    }
}
