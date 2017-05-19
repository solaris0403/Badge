package com.example.badgelibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.badgelibrary.Badge;
import com.example.badgelibrary.IBadge;
import com.example.badgelibrary.db.BadgeTable;

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
            case BadgeTable.STATE_VISIBLE:
                setVisibility(VISIBLE);
                break;
            case BadgeTable.STATE_INVISIBLE:
                setVisibility(INVISIBLE);
                break;
        }
        switch (badge.getDisplayType()) {
            case BadgeTable.TYPE_DOT:
                break;
            case BadgeTable.TYPE_NUMBER:
                break;
            case BadgeTable.TYPE_NEW:
                break;
        }
        setText(String.valueOf(badge.getCount()));
    }
}
