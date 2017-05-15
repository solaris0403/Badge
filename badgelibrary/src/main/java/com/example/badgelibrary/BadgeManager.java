package com.example.badgelibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tony on 5/15/17.
 */

public class BadgeManager {
    private static BadgeManager sInstance = null;

    private BadgeManager() {
    }

    public static BadgeManager getInstance() {
        if (sInstance == null) {
            sInstance = new BadgeManager();
        }
        return sInstance;
    }

    private List<Badge> list = new ArrayList<>();

    public void addBadge(Badge badge) {
        list.add(badge);
    }

    public void removeBadge(Badge badge) {
        list.remove(badge);
    }

    public void update(Badge badge) {
    }
}
