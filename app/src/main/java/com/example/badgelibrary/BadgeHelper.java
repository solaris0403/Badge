package com.example.badgelibrary;

/**
 * Created by tony on 5/15/17.
 */

public class BadgeHelper {
    public static void bindBadge(String owner, IBadge iBadge) {
        BadgeManager.getInstance().bindBadge(owner, iBadge);
    }

    public static void updateBadge(Badge badge) {
        BadgeManager.getInstance().updateBadge(badge);
    }

    public static Badge findBadge(String owner) {
        return DBUtils.queryBadge(owner);
    }
}
