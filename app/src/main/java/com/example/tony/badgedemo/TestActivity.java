package com.example.tony.badgedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.badgelibrary.Badge;
import com.example.badgelibrary.BadgeHelper;
import com.example.badgelibrary.IBadge;
import com.example.badgelibrary.OnBadgeListener;

public class TestActivity extends AppCompatActivity {
    private Button mBtnOne, mBtnTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mBtnOne = (Button) findViewById(R.id.btn_one);
        BadgeHelper.bindBadge(MyBadge.BADGE_TEST_ONE, new IBadge() {
            @Override
            public void display(Badge badge) {
                mBtnOne.setText(String.valueOf(badge.getCount()));
            }
        });
        mBtnTwo = (Button) findViewById(R.id.btn_two);
        BadgeHelper.bindBadge(MyBadge.BADGE_TEST_TWO, new IBadge() {
            @Override
            public void display(Badge badge) {
                mBtnTwo.setText(String.valueOf(badge.getCount()));
            }
        });
        mBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BadgeHelper.updateBadge(MyBadge.BADGE_TEST_ONE, new OnBadgeListener() {
                    @Override
                    public Badge onChange(Badge badge) {
                        badge.setCount(badge.getCount() + 1);
                        return badge;
                    }
                });
            }
        });
        mBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BadgeHelper.updateBadge(MyBadge.BADGE_TEST_TWO, new OnBadgeListener() {
                    @Override
                    public Badge onChange(Badge badge) {
                        badge.setCount(badge.getCount() + 1);
                        return badge;
                    }
                });
            }
        });
    }
}
