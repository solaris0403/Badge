package com.example.badgelibrary;

import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by tony on 5/16/17.
 */

public class DBUtils {
    public static void save(Badge badge) {
        badge.save();
    }

    public static List<Badge> findBadges() {
        return new Select().from(Badge.class).execute();
    }

    public static Badge findBadgeByOwner(String owner) {
        return new Select().from(Badge.class).where("owner = ?", owner).executeSingle();
    }

    public static List<Badge> findBadgesByLeader(String leader) {
        return new Select().from(Badge.class).where("leader = ?", leader).execute();
    }

    public static void deleteBadge(String owner) {

    }
}
