package com.example.badgelibrary;


/**
 * Badge实体类，其中持有View的引用，每次初始化的时候会针对现有的owner进行数据库删除。
 */

public class Badge {
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

    private int mDisplayType = BADGE_DISPLAY_TYPE_DOT;
    private int mDisplayState = BADGE_DISPLAY_STATE_VISIBLE;
    private int mDisplayMode = BADGE_DISPLAY_MODE_AUTO;
    private int mCount;
    private String mContent;
    private String mOwner;
    private String mLeader;

    public Badge(String owner) {
        this.mOwner = owner;
    }

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

    private void setOwner(String owner) {
        this.mOwner = owner;
    }

    public String getLeader() {
        return mLeader;
    }

    public void setLeader(String leader) {
        this.mLeader = leader;
    }

    @Override
    public String toString() {
        return "Badge{" +
                "mDisplayType=" + mDisplayType +
                ", mDisplayState=" + mDisplayState +
                ", mDisplayMode=" + mDisplayMode +
                ", mCount=" + mCount +
                ", mContent='" + mContent + '\'' +
                ", mOwner='" + mOwner + '\'' +
                ", mLeader='" + mLeader + '\'' +
                '}';
    }
}
