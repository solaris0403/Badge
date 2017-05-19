package com.example.badgelibrary;

import android.content.Context;

import com.example.badgelibrary.db.BadgeDao;
import com.example.tony.badgedemo.BadgeApplication;

import java.util.List;

public class DBUtils {
    public static void insertBadge(Context context, Badge badge) {
        BadgeDao dao = new BadgeDao(context);
        dao.insert(badge);
    }

    public static Badge queryBadge(String owner) {
        BadgeDao dao = new BadgeDao(BadgeApplication.getInstance());
        return dao.query(owner);
    }

    public static void updateBadge(Badge badge) {
        BadgeDao dao = new BadgeDao(BadgeApplication.getInstance());
        dao.update(badge);
    }

    public static void deleteBadge(String owner) {
        BadgeDao dao = new BadgeDao(BadgeApplication.getInstance());
        dao.delete(owner);
    }

    public static List<Badge> queryBadges(Context context) {
        BadgeDao dao = new BadgeDao(context);
        return dao.query();
    }
}
