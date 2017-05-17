package com.example.badgelibrary.db;

import android.content.Context;

import com.example.badgelibrary.Badge;

/**
 * Created by tony on 5/17/17.
 */

public abstract class Dao {
    protected DatabaseHelper dbHelper;

    public Dao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    protected abstract void insert(Badge badge);

    protected abstract void delete(String owner);

    protected abstract void update(Badge badge);

    protected abstract Badge query(String owner);

    protected void close() {
        dbHelper.close();
    }
}
