package com.example.badgelibrary;

import android.content.Context;

import com.example.badgelibrary.db.BadgeDao;

import java.util.List;

public class DBUtils {
    public static void insertBadge(Context context, Badge badge) {
        BadgeDao dao = new BadgeDao(context);
        dao.insert(badge);
    }

    public static Badge queryBadge(Context context, String owner) {
        BadgeDao dao = new BadgeDao(context);
        return dao.query(owner);
    }

    public static void updateBadge(Context context, Badge badge) {
        BadgeDao dao = new BadgeDao(context);
        dao.update(badge);
    }

    public static void deleteBadge(Context context, String owner) {
        BadgeDao dao = new BadgeDao(context);
        dao.delete(owner);
    }

    public static List<Badge> queryBadges(Context context) {
        BadgeDao dao = new BadgeDao(context);
        return dao.query();
    }
}
