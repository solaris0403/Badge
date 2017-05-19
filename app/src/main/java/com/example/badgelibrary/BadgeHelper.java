package com.example.badgelibrary;

/**
 * Badge操作类
 */

public class BadgeHelper {
    /**
     * 绑定view
     * @param owner 唯一的标识符
     */
    public static void bindBadge(String owner, IBadge iBadge) {
        BadgeManager.getInstance().bindBadge(owner, iBadge);
    }

    /**
     * 更新已经存在的Badge
     */
    public static void updateBadge(String owner, OnBadgeListener onBadgeListener) {
        BadgeManager.getInstance().updateBadge(owner, onBadgeListener);
    }
}
