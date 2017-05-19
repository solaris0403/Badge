package com.example.tony.badgedemo;


import android.app.Application;

import com.example.badgelibrary.BadgeManager;


/**
 * Created by tony on 5/16/17.
 */

public class BadgeApplication extends Application {
    private static BadgeApplication sInstance = null;
    public static BadgeApplication getInstance(){
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        BadgeManager.getInstance().init(this, new MyBadge());
    }
}
