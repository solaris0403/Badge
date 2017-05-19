package com.example.tony.badgedemo;

import com.example.badgelibrary.Badge;
import com.example.badgelibrary.IBadgeConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tony on 5/19/17.
 */

public class MyBadge implements IBadgeConfig {
    public static final String BADGE_MAIN_ONE = "badge_main_one";
    public static final String BADGE_MAIN_TWO = "badge_main_two";
    public static final String BADGE_TEST_ONE = "badge_test_one";
    public static final String BADGE_TEST_TWO = "badge_test_two";

    @Override
    public Map<String, Badge> initializeNewBadges() {
        Map<String, Badge> map = new HashMap<>();
        //main
        Badge mainOne = new Badge(BADGE_MAIN_ONE);
        map.put(mainOne.getOwner(), mainOne);
        //test one
        Badge testOne = new Badge(BADGE_TEST_ONE);
        testOne.setLeader(BADGE_MAIN_ONE);
        map.put(testOne.getOwner(), testOne);
        Badge testTwo = new Badge(BADGE_TEST_TWO);
        testTwo.setLeader(BADGE_MAIN_ONE);
        map.put(testTwo.getOwner(), testTwo);
        return map;
    }
}
