package com.example.badgelibrary;

import java.util.Map;

/**
 * Created by tony on 5/18/17.
 */

public interface IBadgeConfig {
    /**
     * 初始化用户预置的badge
     *
     * @return 所有需要的badge
     */
    Map<String, Badge> initializeNewBadges();
}
