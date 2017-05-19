package com.example.tony.badgedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.badgelibrary.Badge;
import com.example.badgelibrary.BadgeHelper;
import com.example.badgelibrary.widget.NumBadge;

public class MainActivity extends AppCompatActivity {
    private Button mBtnOne;
    private NumBadge numBadge1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnOne = (Button) findViewById(R.id.btn_one);
        numBadge1 = (NumBadge) findViewById(R.id.badge_1);
        BadgeHelper.bindBadge(MyBadge.BADGE_MAIN_ONE, numBadge1);
        mBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Badge badge = BadgeHelper.findBadge(MyBadge.BADGE_MAIN_ONE);
                badge.setCount(badge.getCount() + 1);
                BadgeHelper.updateBadge(badge);
            }
        });
    }
}
