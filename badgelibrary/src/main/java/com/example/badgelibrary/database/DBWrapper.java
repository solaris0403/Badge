package com.example.badgelibrary.database;

import android.content.Context;

/**
 * Created by tony on 5/15/17.
 */

class DBWrapper {
    private static final DBWrapper ourInstance = new DBWrapper();

    static DBWrapper getInstance(Context context) {
        return ourInstance;
    }

    private BadgeDBOpenHelper mBadgeDBOpenHelper;

    private DBWrapper() {
        mBadgeDBOpenHelper = new BadgeDBOpenHelper(null);
    }

}
