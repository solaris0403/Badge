package com.example.badgelibrary;

/**
 * Badge操作类
 */

public class BadgeHelper {
    /**
     * 绑定view
     * @param name 唯一的标识符
     */
    public static void bindBadge(String name, IBadge iBadge) {
        BadgeManager.getInstance().bindBadge(name, iBadge);
    }

    public static void bindBadge(IBadgeListener listener){

    }

    /**
     * 绑定view
     * @param name 唯一的标识符
     */
    public static void unbindBadge(String name) {
        BadgeManager.getInstance().unbindBadge(name);
    }

    /**
     * 更新已经存在的Badge
     */
    public static void updateBadge(String owner, OnBadgeListener onBadgeListener) {
        BadgeManager.getInstance().updateBadge(owner, onBadgeListener);
    }
}
