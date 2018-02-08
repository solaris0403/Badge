package com.example.badgelibrary;

import android.content.Context;

import com.example.badgelibrary.db.BadgeDao;

import java.util.List;

public class DBUtils {
    public static void insertBadge(Context context, Badge badge) {
        BadgeDao dao = new BadgeDao(context);
        dao.insert(badge);
    }

    public static Badge queryBadge(Context context, String name) {
        BadgeDao dao = new BadgeDao(context);
        return dao.query(name);
    }

    public static void updateBadge(Context context, Badge badge) {
        BadgeDao dao = new BadgeDao(context);
        dao.update(badge);
    }

    public static void deleteBadge(Context context, String name) {
        BadgeDao dao = new BadgeDao(context);
        dao.delete(name);
    }

    public static List<Badge> queryBadges(Context context) {
        BadgeDao dao = new BadgeDao(context);
        return dao.query();
    }
}
