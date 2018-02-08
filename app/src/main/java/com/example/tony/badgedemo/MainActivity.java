package com.example.tony.badgedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.badgelibrary.Badge;
import com.example.badgelibrary.BadgeHelper;
import com.example.badgelibrary.IBadgeListener;
import com.example.badgelibrary.OnBadgeListener;
import com.example.badgelibrary.widget.NumBadge;

public class MainActivity extends AppCompatActivity {
    private Button mBtnOne, mBtnTwo;
    private NumBadge numBadge1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnOne = (Button) findViewById(R.id.btn_one);
        mBtnTwo = (Button) findViewById(R.id.btn_two);
        numBadge1 = (NumBadge) findViewById(R.id.badge_1);
        BadgeHelper.bindBadge(MyBadge.BADGE_MAIN_ONE, numBadge1);
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
        BadgeHelper.bindBadge(new IBadgeListener(MyBadge.BADGE_TEST_ONE) {
            @Override
            public void update(Badge badge) {

            }
        });
        mBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BadgeHelper.unbindBadge(MyBadge.BADGE_MAIN_ONE);
    }
}
