package com.example.badgelibrary;

import com.example.badgelibrary.db.BadgeDao;
import com.example.tony.badgedemo.BadgeApplication;

/**
 * Created by tony on 5/16/17.
 */

public class DBUtils {
    public static void insertBadge(Badge badge) {
        BadgeDao dao = new BadgeDao(BadgeApplication.getInstance());
        dao.insert(badge);
    }

    public static Badge findBadgeByOwner(String owner) {
        BadgeDao dao = new BadgeDao(BadgeApplication.getInstance());
        Badge badge = dao.query(owner);
        if (badge == null){
            insertBadge(new Badge(owner));
            badge = findBadgeByOwner(owner);
        }
        return badge;
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
