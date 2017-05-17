package com.example.badgelibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.badgelibrary.Badge;
import com.example.badgelibrary.IBadge;

/**
 * Created by tony on 5/15/17.
 */

public class NumBadge extends TextView implements IBadge {
    private Badge mBadge;

    public NumBadge(Context context) {
        this(context, null);
    }

    public NumBadge(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumBadge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void display(Badge badge) {
        switch (badge.getDisplayState()) {
            case Badge.BADGE_DISPLAY_STATE_VISIBLE:
                setVisibility(VISIBLE);
                break;
            case Badge.BADGE_DISPLAY_STATE_INVISIBLE:
                setVisibility(INVISIBLE);
                break;
        }
        switch (badge.getDisplayType()) {//更改视图类型
            case Badge.BADGE_DISPLAY_TYPE_DOT:
                break;
            case Badge.BADGE_DISPLAY_TYPE_CIRCLE:
                break;
            case Badge.BADGE_DISPLAY_TYPE_NEW:
                break;
        }
        setText(String.valueOf(badge.getCount()));
    }
}
