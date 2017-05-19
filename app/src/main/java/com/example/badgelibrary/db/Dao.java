package com.example.badgelibrary.db;

import android.content.Context;

import com.example.badgelibrary.Badge;

import java.util.List;

/**
 * Created by tony on 5/17/17.
 */

public abstract class Dao {
    protected DatabaseHelper dbHelper;

    public Dao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public abstract void insert(Badge badge);

    public abstract void delete(String owner);

    public abstract void update(Badge badge);

    public abstract Badge query(String owner);

    public abstract List<Badge> query();

    protected void close() {
        dbHelper.close();
    }
}
