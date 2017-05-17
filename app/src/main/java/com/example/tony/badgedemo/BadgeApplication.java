package com.example.tony.badgedemo;


import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.example.badgelibrary.BadgeManager;

/**
 * Created by tony on 5/16/17.
 */

public class BadgeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BadgeManager.getInstance().init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
