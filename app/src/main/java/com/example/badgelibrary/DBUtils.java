package com.example.badgelibrary;

import com.example.badgelibrary.db.BadgeDao;
import com.example.tony.badgedemo.BadgeApplication;

public class DBUtils {
    public static void insertBadge(Badge badge) {
        BadgeDao dao = new BadgeDao(BadgeApplication.getInstance());
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
}
