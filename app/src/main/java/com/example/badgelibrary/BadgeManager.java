package com.example.badgelibrary;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Badge管理类
 * 整体架构为单向数据流，每次更新badge则直接对数数据库进行操作，并且重新构建整个badge的依赖关系，之后根据新的依赖关系更新view。
 * 1. badge树模型的构建
 * 2. 对view的管理
 * 3. 每次操作数据库则全量更新view，确保显示与存储的一致性
 */
public class BadgeManager {
    private static final String TAG = BadgeManager.class.getSimpleName();
    private static BadgeManager sInstance = null;
    private Context mContext;
    private IBadgeConfig mBadgeConfig;
    private ExecutorService mExecutor;
    private Handler mUIHandler;

    private BadgeManager() {
        mExecutor = Executors.newSingleThreadExecutor();
        mUIHandler = new Handler(Looper.getMainLooper());
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
        initBadge();
    }

    /**
     * 根据用户配置插入新数据
     */
    private void initBadge() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (mBadgeConfig != null) {
                    Map<String, Badge> map = mBadgeConfig.initializeNewBadges();
                    if (map != null) {
                        for (String name : map.keySet()) {
                            DBUtils.insertBadge(mContext, map.get(name));
                        }
                    }
                }
                generateBadgeTree();
            }
        });
    }

    //存储数据库中所有的数据
    private Map<String, Badge> mBadgeMap = new HashMap<>();
    //所有绑定成功的ui接口
    private Map<String, IBadge> mUIMap = new HashMap<>();

    /**
     * 每次对数据库更新都要重新构造一个树
     */
    private void generateBadgeTree() {
        mBadgeMap.clear();
        List<Badge> badges = DBUtils.queryBadges(mContext);
        //把所有元素加入map中
        for (Badge badge : badges) {
            mBadgeMap.put(badge.getName(), badge);
        }
        //二次对所有有父节点的数据进行关联
        for (Badge badge : badges) {
            if (!TextUtils.isEmpty(badge.getParent())) {
                mBadgeMap.get(badge.getParent()).addChild(badge);
            }
        }
    }

    /**
     * 绑定view，并且根据数据库中的数据进行更新
     */
    public void bindBadge(String name, IBadge iBadge) {
        Log.i(TAG, "bindBadge:" + name);
        Badge badge = mBadgeMap.get(name);
        if (badge != null) {
            mUIMap.put(name, iBadge);
            updateBadgeUI(badge);
        }
    }

    public void unbindBadge(String name) {
        mUIMap.remove(name);
    }

    /**
     * 直接对数据库进行修改，之后重建树模型，并根据最新数据更新存在的UI
     * 注意：
     * 更新单一badge计数之后父节点的计数也会改变，但由于getCount()调用时会深层遍历，
     * 所以再次全量更新数据库是没有必要的，只要确保当前节点的计数准确即可。
     */
    public void updateBadge(final String name, final OnBadgeListener listener) {
        Log.i(TAG, "updateBadge:" + name);
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Badge badge = mBadgeMap.get(name);
                if (listener != null && badge != null) {
                    DBUtils.updateBadge(mContext, listener.onChange(badge));
                    generateBadgeTree();
                    updateBadgesUI();
                }
            }
        });
    }

    /**
     * ui单一更新
     */
    private void updateBadgeUI(Badge badge) {
        IBadge iBadge = mUIMap.get(badge.getName());
        if (iBadge != null) {
            iBadge.display(badge);
        }
    }

    /**
     * ui全量更新
     */
    private void updateBadgesUI() {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                for (String name : mUIMap.keySet()) {
                    IBadge iBadge = mUIMap.get(name);
                    Badge badge = mBadgeMap.get(name);
                    if (iBadge != null && badge != null) {
                        iBadge.display(badge);
                    }
                }
            }
        });
    }
}
