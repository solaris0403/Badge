package com.example.badgelibrary;

/**
 * 更新回调接口，可通过该接口返回数据库中的badge进行修改
 */

public interface OnBadgeListener {
    Badge onChange(Badge badge);
}
