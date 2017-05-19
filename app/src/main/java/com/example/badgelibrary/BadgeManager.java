package com.example.badgelibrary;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Badge管理类
 * 整体架构为单向数据流，每次更新badge则直接对数数据库进行操作，并且重新构建整个badge的依赖关系，之后根据新的依赖关系更新view。
 * 1. badge树模型的构建
 * 2. 对view的管理
 * 3. 每次操作数据库则全量更新view，确保显示与存储的一致性
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
     * 根据用户配置插入新数据
     */
    private void init() {
        if (mBadgeConfig != null) {
            Map<String, Badge> map = mBadgeConfig.initializeNewBadges();
            if (map != null) {
                for (String owner : map.keySet()) {
                    DBUtils.insertBadge(mContext, map.get(owner));
                }
            }
        }
        generateBadgeTree();
    }

    //存储数据库中所有的数据
    private Map<String, Badge> mBadgeMap = new HashMap<>();
    //所有绑定成功的ui接口
    private Map<String, SoftReference<IBadge>> mUIMap = new HashMap<>();

    /**
     * 每次对数据库更新都要重新构造一个树
     */
    private void generateBadgeTree() {
        mBadgeMap.clear();
        List<Badge> badges = DBUtils.queryBadges(mContext);
        //把所有元素加入map中
        for (Badge badge : badges) {
            mBadgeMap.put(badge.getOwner(), badge);
        }
        //二次对所有有父节点的数据进行关联
        for (Badge badge : badges) {
            if (!TextUtils.isEmpty(badge.getLeader())) {
                mBadgeMap.get(badge.getLeader()).addChild(badge);
            }
        }
    }

    /**
     * 绑定view，并且根据数据库中的数据进行更新
     */
    public void bindBadge(String owner, IBadge iBadge) {
        Log. (TAG, "bindBadge:" + owner);
        Badge badge = mBadgeMap.get(owner);
        if (badge != null) {
            mUIMap.put(badge.getOwner(), new SoftReference<>(iBadge));
            updateBadgeUI(badge);
        }
    }

    /**
     * 直接对数据库进行修改，之后重建树模型，并根据最新数据更新存在的UI
     * 注意：
     * 更新单一badge计数之后父节点的计数也会改变，但由于getCount()方法使用深层遍历，
     * 所以再次全量更新数据库是没有必要的，只要确保当前节点的计数准确即可。
     */
    public void updateBadge(String owner, OnBadgeListener listener) {
        Log.i(TAG, "updateBadge:" + owner);
        Badge badge = mBadgeMap.get(owner);
        if (listener != null && badge != null) {
            DBUtils.updateBadge(mContext, listener.onChange(badge));
            generateBadgeTree();
            updateBadgesUI();
        }
    }

    /**
     * ui单一更新
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

    /**
     * ui全量更新
     */
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
