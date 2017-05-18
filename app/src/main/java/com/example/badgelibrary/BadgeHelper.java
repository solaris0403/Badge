package com.example.badgelibrary;

/**
 * Created by tony on 5/15/17.
 */

public class BadgeHelper {
    public static final String BADGE_MAIN_ONE = "badge_main_one";
    public static final String BADGE_MAIN_TWO = "badge_main_two";
    public static final String BADGE_TEST_ONE = "badge_test_one";
    public static final String BADGE_TEST_TWO = "badge_test_two";

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
