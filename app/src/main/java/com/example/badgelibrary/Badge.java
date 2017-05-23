package com.example.badgelibrary;

import com.example.badgelibrary.db.BadgeTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Badge实体类，利用List持有逻辑子元素的引用，进行遍历更新依赖。
 */

public class Badge implements Operator {
    private int mDisplayType = BadgeTable.TYPE_NUMBER;
    private int mDisplayState = BadgeTable.STATE_VISIBLE;
    private int mDisplayMode = BadgeTable.MODE_AUTO;
    private int mCount;
    private String mContent;
    private String mOwner;
    private String mLeader;
    private List<Badge> mChild = new ArrayList<>();

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

    /**
     * 通过深层遍历，累加子元素所有的计数
     */
    public int getCount() {
        if (!mChild.isEmpty()) {
            mCount = 0;
            for (Badge badge : mChild) {
                mCount += badge.getCount();
            }
        }
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

    public String getLeader() {
        return mLeader;
    }

    public void setLeader(String leader) {
        this.mLeader = leader;
    }

    public List<Badge> getChild() {
        return mChild;
    }

    public void addChild(Badge child) {
        this.mChild.add(child);
    }

    @Override
    public void read() {
        setCount(0);
        if (!mChild.isEmpty()) {
            for (Badge badge : mChild) {
                badge.read();
            }
        }
    }

    @Override
    public void unread() {
        setCount(1);
    }
}
