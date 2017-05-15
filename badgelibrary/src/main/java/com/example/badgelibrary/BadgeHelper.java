package com.example.badgelibrary;

/**
 * Created by tony on 5/15/17.
 */

public class BadgeHelper {
    public static void bindBadge(Badge badge) {
        BadgeManager.getInstance().addBadge(badge);
    }

    public static void unbindBadge(Badge badge) {
        BadgeManager.getInstance().removeBadge(badge);
    }

    public static void updateBadge(Badge badge) {
        BadgeManager.getInstance().update(badge);
    }
}
