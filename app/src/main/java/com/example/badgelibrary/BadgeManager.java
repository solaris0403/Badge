package com.example.badgelibrary;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.badgelibrary.db.BadgeDao;
import com.example.tony.badgedemo.BadgeApplication;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import static com.example.badgelibrary.DBUtils.insertBadge;

/**
 * 绑定，如果没有，则将数据进行写入更新，
 * 如果数据库中有，则直接更新
 * <p>
 * 更新，更新数据库，更新
 */
public class BadgeManager {
    private static final String TAG = "BadgeManager";
    private static BadgeManager sInstance = null;
    private Context mContext;
    private IBadgeConfig mBadgeConfig;

    private BadgeManager() {
    }

    public static BadgeManager getInstance() {
        if (sInstance == null) {
            sInstance = new BadgeManager();
        }
        return sInstance;
    }

    public void init(Context context) {
        init(context, null);
    }

    public void init(Context context, IBadgeConfig iBadgeConfig) {
        this.mContext = context;
        this.mBadgeConfig = iBadgeConfig;
        init();
    }

    /**
     * 对于数据库中没有的直接进行插入
     */
    private void init() {
        if (mBadgeConfig != null) {
            Map<String, Badge> map = mBadgeConfig.initializeNewBadges();
            for (String owner : map.keySet()) {
                Log.i(TAG, "init insert:" + owner);
                DBUtils.insertBadge(mContext, map.get(owner));
            }
        }
    }

    //当前应用中保存的ui记录，用于更新
    private Map<String, SoftReference<IBadge>> uimap = new HashMap<>();


    public void bindBadge(String owner, IBadge iBadge) {
        Log.i(TAG, "bindBadge:" + owner);
        uimap.put(owner, new SoftReference<>(iBadge));
        Badge dbbadge = findBadge(owner);
        if (dbbadge != null) {
            updateRelation(dbbadge);
        }
    }

    /**
     * 直接对数据库进行修改，存在的UI也会更新
     *
     * @param uibadge
     */
    public void updateBadge(Badge uibadge) {
        Log.i(TAG, "updateBadge:" + uibadge.toString());
        if (findBadge(uibadge.getOwner()) == null) {
            insertBadge(mContext, uibadge);
        }
        updateRelation(uibadge);
    }

    public void unbindBadge(String owner) {
        uimap.remove(owner);
        DBUtils.deleteBadge(owner);
    }

    /**
     * 已经修改了当前的
     * 更新数据库中的关系
     * <p>
     * 目前没有针对修改子节点的需求，所以可以只提供修改父节点的功能
     */
    private void updateRelation(Badge badge) {
        int badgeOldCount = findBadge(badge.getOwner()).getCount();
        int badgeNewCount = badge.getCount();
        DBUtils.updateBadge(badge);
        updateBadgeUI(badge);
        String leader = badge.getLeader();
        if (!TextUtils.isEmpty(leader)) {
            //对夫节点进行修改
            Badge leaderBadge = findBadge(leader);
            if (leaderBadge != null) {
                leaderBadge.setCount(leaderBadge.getCount() + (badgeNewCount - badgeOldCount));
                //递归修改父节点
                updateRelation(leaderBadge);
            }
        }
    }

    /**
     * 内部调用有可能为空
     *
     * @param owner
     * @return
     */
    private Badge findBadge(String owner) {
        BadgeDao dao = new BadgeDao(BadgeApplication.getInstance());
        return dao.query(owner);
    }

    /**
     * 更新界面
     *
     * @param badge 从数据库中拿到的badge
     */
    private void updateBadgeUI(Badge badge) {
        Log.e(TAG, "updateBadgeUI:" + badge.toString());
        SoftReference<IBadge> softReference = uimap.get(badge.getOwner());
        if (softReference != null) {
            IBadge iBadge = softReference.get();
            if (iBadge != null) {
                iBadge.display(badge);
            }
        }
    }
}
