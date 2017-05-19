package com.example.badgelibrary;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 插入新的数据
     */
    private void init() {
        if (mBadgeConfig != null) {
            Map<String, Badge> map = mBadgeConfig.initializeNewBadges();
            for (String owner : map.keySet()) {
                DBUtils.insertBadge(mContext, map.get(owner));
            }
        }
        generateBadgeTree();
    }

    private Map<String, Badge> mBadgeMap = new HashMap<>();
    private Map<String, SoftReference<IBadge>> mUIMap = new HashMap<>();

    /**
     * 每次对数据库更新都要重新构造一个数据树
     */
    private void generateBadgeTree() {
        mBadgeMap.clear();
        List<Badge> allBadges = DBUtils.queryBadges(mContext);
        for (Badge badge : allBadges) {
            mBadgeMap.put(badge.getOwner(), badge);
        }
        for (Badge badge : allBadges) {
            if (!TextUtils.isEmpty(badge.getLeader())) {
                mBadgeMap.get(badge.getLeader()).addChild(badge);
            }
        }
    }

    /**
     * 绑定view，并且更新
     *
     * @param owner
     * @param iBadge
     */
    public void bindBadge(String owner, IBadge iBadge) {
        Log.i(TAG, "bindBadge:" + owner);
        Badge badge = mBadgeMap.get(owner);
        if (badge != null) {
            mUIMap.put(badge.getOwner(), new SoftReference<>(iBadge));
            updateBadgeUI(badge);
        }
    }

    /**
     * 直接对数据库进行修改，存在的UI也会更新
     */
    public void updateBadge(String owner, OnBadgeListener listener) {
        Badge badge = mBadgeMap.get(owner);
        if (listener != null && badge != null) {
            DBUtils.updateBadge(mContext, listener.onChange(badge));
            generateBadgeTree();
            updateBadgesUI();
        }
    }

    /**
     * 更新界面
     */
    private void updateBadgeUI(Badge badge) {
        SoftReference<IBadge> softReference = mUIMap.get(badge.getOwner());
        if (softReference != null) {
            IBadge iBadge = softReference.get();
            if (iBadge != null) {
                iBadge.display(badge);
            }
        }
    }

    private void updateBadgesUI() {
        for (String owner : mUIMap.keySet()) {
            SoftReference<IBadge> softReference = mUIMap.get(owner);
            if (softReference != null) {
                IBadge iBadge = softReference.get();
                Badge badge = mBadgeMap.get(owner);
                if (iBadge != null && badge != null) {
                    iBadge.display(badge);
                }
            }
        }
    }
}
