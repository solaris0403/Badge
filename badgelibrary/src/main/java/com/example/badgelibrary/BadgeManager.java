package com.example.badgelibrary;

import android.content.Context;
import android.text.TextUtils;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 绑定，如果没有，则将数据进行写入更新，
 * 如果数据库中有，则直接更新
 * <p>
 * 更新，更新数据库，更新
 */
public class BadgeManager {
    private static BadgeManager sInstance = null;

    private BadgeManager() {
    }

    public void init(Context context) {
        Configuration dbConfiguration = new Configuration.Builder(context)
                .setDatabaseName("badge.db")
                .setDatabaseVersion(1)
                .create();
        ActiveAndroid.initialize(dbConfiguration);
    }

    public static BadgeManager getInstance() {
        if (sInstance == null) {
            sInstance = new BadgeManager();
        }
        return sInstance;
    }

    //当前应用中保存的ui记录，用于更新
    private List<Badge> uilist = new ArrayList<>();


    //绑定视图,该badge持有view
    public void bindBadge(Badge badge, IBadge iBadge) {
        badge.setIBadge(iBadge);
        boolean isContains = false;
        for (Badge badge_ui : uilist) {
            if (badge.getOwner().equals(badge_ui.getOwner())) {
                //更新引用，防止视图错误
                badge_ui.setIBadge(iBadge);
                isContains = true;
                break;
            }
        }
        if (!isContains) {
            uilist.add(badge);
        }
        updateBadge(badge);
    }

    //直接对数据库进行修改，存在的UI也会更新
    public void updateBadge(Badge badge) {
        updateBD(badge);
    }

    public void unbindBadge(Badge badge) {
        DBUtils.deleteBadge(badge.getOwner());
    }


    /**
     * 更新数据库
     *
     * @param badge
     */
    private void updateBD(Badge badge) {
        //初次插入
        if (DBUtils.findBadgeByOwner(badge.getOwner()) == null) {
            DBUtils.save(badge);
        }
        //有可能从数据库中查到的
        updateRelation(badge);
    }

    /**
     * 已经修改了当前的
     * 更新数据库中的关系
     * <p>
     * 目前没有针对修改子节点的需求，所以可以只提供修改父节点的功能
     */
    private void updateRelation(Badge badge) {
        //更新UI
        updateBadgeUI(badge);
        String leader = badge.getLeader();
        if (!TextUtils.isEmpty(leader)) {
            //对夫节点进行修改
            Badge b = DBUtils.findBadgeByOwner(leader);
            b.setCount(badge.getCount() + b.getCount());
            DBUtils.save(b);
            //递归修改父节点
            updateRelation(b);
        }
    }

    /**
     * 更新界面
     *
     * @param badge 从数据库中拿到的badge
     */
    private void updateBadgeUI(Badge badge) {
        for (Badge badge1 : uilist) {
            if (badge.getOwner().equals(badge1.getOwner())) {
                badge.setIBadge(badge1.getIBadge());
                break;
            }
        }
        IBadge iBadge = badge.getIBadge();
        if (iBadge != null) {
            iBadge.setDisplayType(badge.getDisplayType());
            iBadge.setDisplayState(badge.getDisplayState());
            iBadge.setDisplayContent(badge.getContent());
            iBadge.setDisplayCount(badge.getCount());
        }
    }
}
