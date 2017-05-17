package com.example.tony.badgedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.badgelibrary.Badge;
import com.example.badgelibrary.BadgeHelper;
import com.example.badgelibrary.widget.NumBadge;

public class MainActivity extends AppCompatActivity {
    private Button mBtnOne, mBtnTwo;
    private NumBadge numBadge1, numBadge2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnOne = (Button) findViewById(R.id.btn_one);
        mBtnTwo = (Button) findViewById(R.id.btn_two);
        numBadge1 = (NumBadge) findViewById(R.id.badge_1);
        BadgeHelper.bindBadge(BadgeHelper.BADGE_MAIN_ONE, numBadge1);
        numBadge2 = (NumBadge) findViewById(R.id.badge_2);
        BadgeHelper.bindBadge(BadgeHelper.BADGE_MAIN_TWO, numBadge2);
        mBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Badge badge = BadgeHelper.findBadge(BadgeHelper.BADGE_TEST_ONE);
                badge.setCount(badge.getCount() + 1);
                badge.setLeader(BadgeHelper.BADGE_MAIN_ONE);
                BadgeHelper.updateBadge(badge);
            }
        });
        mBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
    }
}
