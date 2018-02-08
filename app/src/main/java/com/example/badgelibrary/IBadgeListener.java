package com.example.badgelibrary;

/**
 * Created by tony on 2018/2/6.
 */

public abstract class IBadgeListener {
    private String name;

    public IBadgeListener(String name) {
        this.name = name;
    }

    public abstract void update(Badge badge);
}
