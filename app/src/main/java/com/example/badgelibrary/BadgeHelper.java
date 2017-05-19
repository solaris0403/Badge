package com.example.badgelibrary;

/**
 * Created by tony on 5/15/17.
 */

public class BadgeHelper {
    public static void bindBadge(String owner, IBadge iBadge) {
        BadgeManager.getInstance().bindBadge(owner, iBadge);
    }

    public static void updateBadge(String owner, OnBadgeListener onBadgeListener) {
        BadgeManager.getInstance().updateBadge(owner, onBadgeListener);
    }
}
