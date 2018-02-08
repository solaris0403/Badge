package com.example.badgelibrary.db;

import android.content.Context;

import com.example.badgelibrary.Badge;

import java.util.List;

/**
 * Created by tony on 5/17/17.
 */

public abstract class Dao {
    protected BadgeDBHelper dbHelper;

    public Dao(Context context) {
        dbHelper = new BadgeDBHelper(context);
    }

    public abstract void insert(Badge badge);

    public abstract void delete(String name);

    public abstract void update(Badge badge);

    public abstract Badge query(String name);

    public abstract List<Badge> query();

    public void close() {
        dbHelper.close();
    }
}
