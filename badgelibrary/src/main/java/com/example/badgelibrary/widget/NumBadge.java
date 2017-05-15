package com.example.badgelibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.badgelibrary.Badge;
import com.example.badgelibrary.IBadge;

/**
 * Created by tony on 5/15/17.
 */

public class NumBadge extends TextView implements IBadge {

    public NumBadge(Context context) {
        this(context, null);
    }

    public NumBadge(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumBadge(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {

    }

    @Override
    public void setDisplayType(int displayType) {
        switch (displayType) {//更改视图类型
            case Badge.BADGE_DISPLAY_TYPE_DOT:
                break;
            case Badge.BADGE_DISPLAY_TYPE_CIRCLE:
                break;
            case Badge.BADGE_DISPLAY_TYPE_NEW:
                break;
        }
    }

    @Override
    public void setDisplayState(int displayState) {
        switch (displayState) {
            case Badge.BADGE_DISPLAY_STATE_VISIBLE:
                setVisibility(VISIBLE);
                break;
            case Badge.BADGE_DISPLAY_STATE_INVISIBLE:
                setVisibility(INVISIBLE);
                break;
        }
    }

    @Override
    public void setDisplayContent(String content) {
        setText(content);
    }
}
