package com.example.badgelibrary;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.lang.ref.SoftReference;

/**
 * Badge实体类，其中持有View的引用，每次初始化的时候会针对现有的owner进行数据库删除。
 */

@Table(name = "tb_badge")
public class Badge extends Model {
    //显示形式
    public static final int BADGE_DISPLAY_TYPE_DOT = 0x01;
    public static final int BADGE_DISPLAY_TYPE_CIRCLE = 0x01 << 1;
    public static final int BADGE_DISPLAY_TYPE_NEW = 0x01 << 2;
    //显示状态
    public static final int BADGE_DISPLAY_STATE_VISIBLE = 0x02;
    public static final int BADGE_DISPLAY_STATE_INVISIBLE = 0x02 << 1;
    //显示模式,自动/手动
    public static final int BADGE_DISPLAY_MODE_AUTO = 0x03;
    public static final int BADGE_DISPLAY_MODE_MANUAL = 0x03 << 1;

    @Column(name = "type")
    public int mDisplayType = BADGE_DISPLAY_TYPE_DOT;
    @Column(name = "state")
    public int mDisplayState = BADGE_DISPLAY_STATE_VISIBLE;
    @Column(name = "mode")
    public int mDisplayMode = BADGE_DISPLAY_MODE_AUTO;
    @Column(name = "count")
    public int mCount;
    @Column(name = "content")
    public String mContent;
    @Column(name = "owner", notNull = true, unique = true)
    public String mOwner;
    @Column(name = "leader")
    public String mLeader;
    private SoftReference<IBadge> mIBadge;

    public int getDisplayType() {
        return mDisplayType;
    }

    public void setDisplayType(int displayType) {
        this.mDisplayType = displayType;
    }

    public int getDisplayState() {
        return mDisplayState;
    }

    public void setDisplayState(int displayState) {
        this.mDisplayState = displayState;
    }

    public int getDisplayMode() {
        return mDisplayMode;
    }

    public void setDisplayMode(int displayMode) {
        this.mDisplayMode = displayMode;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        this.mOwner = owner;
    }

    public String getLeader() {
        return mLeader;
    }

    public void setLeader(String leader) {
        this.mLeader = leader;
    }

    public IBadge getIBadge() {
        return mIBadge.get();
    }

    public void setIBadge(IBadge iBadge) {
        this.mIBadge = new SoftReference<>(iBadge);
    }
}
