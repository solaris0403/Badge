package com.example.badgelibrary;

/**
 * Created by tony on 5/15/17.
 */

public class BadgeHelper {
    public static final String BADGE_ONE = "badge_one";
    public static final String BADGE_TWO = "badge_two";

    /**
     * 往数据库中添加row，如果有的话则不添加直接使用数据库中的内容进行更新。
     *
     * @param badge
     */
    public static void bindBadge(Badge badge, IBadge iBadge) {
        BadgeManager.getInstance().bindBadge(badge, iBadge);
    }

    public static Badge findBadge(String owner){
        return DBUtils.findBadgeByOwner(owner);
    }

    /**
     * 更新当前Badge，并修改与之有关的所有Badge
     *
     * @param badge
     */
    public static void updateBadge(Badge badge) {
        BadgeManager.getInstance().updateBadge(badge);
    }
}
