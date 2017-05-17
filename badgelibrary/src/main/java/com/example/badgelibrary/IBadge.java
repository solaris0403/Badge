package com.example.badgelibrary;

/**
 * Created by tony on 5/15/17.
 */

public interface IBadge {
    /**
     * 显示模型 1.小圆点 2.数字红点 3.new标签
     *
     * @param displayType
     */
    void setDisplayType(int displayType);

    /**
     * 显示状态 1.显示 2.隐藏
     *
     * @param displayState
     */
    void setDisplayState(int displayState);

    /**
     * 通常显示数字
     *
     * @param content
     */
    void setDisplayContent(String content);

    void setDisplayCount(int count);
}
