package com.example.tony.badgedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.badgelibrary.Badge;
import com.example.badgelibrary.BadgeHelper;
import com.example.badgelibrary.widget.NumBadge;

public class TestActivity extends AppCompatActivity {
    private Button mBtnTwo;
    private NumBadge mNumBadge;
    Badge badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mBtnTwo = (Button) findViewById(R.id.btn_two);
        mNumBadge = (NumBadge) findViewById(R.id.badge);
        badge = new Badge();
        badge.setOwner(BadgeHelper.BADGE_TWO);
        badge.setIBadge(mNumBadge);
        BadgeHelper.bindBadge(badge, mNumBadge);
        mBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Badge badge = BadgeHelper.findBadge(BadgeHelper.BADGE_TWO);
                badge.setCount(badge.getCount() + 1);
                BadgeHelper.updateBadge(badge);
            }
        });
    }
}
