package com.example.badgelibrary;

import java.util.Map;

/**
 * Created by tony on 5/18/17.
 */

public interface IBadgeConfig {
    /**
     * 添加数据库中没有的新记录
     * @return
     */
    Map<String, Badge> initializeNewBadges();
}
