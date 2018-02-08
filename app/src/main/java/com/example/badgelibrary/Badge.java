package com.example.badgelibrary;

import com.example.badgelibrary.db.BadgeTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Badge实体类，利用List持有逻辑子元素的引用，进行遍历更新依赖。
 */

public class Badge implements Operator {
    private String user;
    private String name;
    private String parent;
    private int state;
    private int mode;
    private int count;
    private String content;

    private List<Badge> child = new ArrayList<>();

    public Badge(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 通过深层遍历，累加子元素所有的计数
     */
    public int getCount() {
        if (mode == BadgeTable.MODE_AUTO) {
            count = 0;
            for (Badge badge : child) {
                count += badge.getCount();
            }
        }
        return count;
    }


    public List<Badge> getChild() {
        return child;
    }

    public void addChild(Badge child) {
        this.child.add(child);
    }

    @Override
    public void read() {
        if (mode == BadgeTable.MODE_AUTO) {
            for (Badge badge : child) {
                badge.read();
            }
        }
        setCount(0);
        setState(BadgeTable.STATE_NORMAL);
    }

    @Override
    public void unread() {
        setCount(1);
        setState(BadgeTable.STATE_VISIBLE);
    }
}
