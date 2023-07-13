package com.example.yg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class instruction_screen extends AppCompatActivity {

    ViewPager mSLideViewPager2;
    LinearLayout mDotLayout2;
    Button backbtn2, nextbtn2;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_screen);

        backbtn2 = findViewById(R.id.backbtn2);
        nextbtn2 = findViewById(R.id.nextbtn2);

        backbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) > 0){

                    mSLideViewPager2.setCurrentItem(getitem(-1),true);

                }

            }
        });

        nextbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 3)
                    mSLideViewPager2.setCurrentItem(getitem(1),true);
                else {

                    Intent i = new Intent(instruction_screen.this,login_page.class);
                    startActivity(i);
                    finish();

                }

            }
        });


        mSLideViewPager2 = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout2 = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPagerAdapter = new ViewPagerAdapter(this);

        mSLideViewPager2.setAdapter(viewPagerAdapter);

        setUpindicator(0);
        mSLideViewPager2.addOnPageChangeListener(viewListener);

    }

    @SuppressLint("NewApi")
    public void setUpindicator(int position){

        dots = new TextView[4];
        mDotLayout2.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++){

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotLayout2.addView(dots[i]);

        }

        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

            if (position > 0){

                backbtn2.setVisibility(View.VISIBLE);

            }else {

                backbtn2.setVisibility(View.INVISIBLE);

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i){

        return mSLideViewPager2.getCurrentItem() + i;
    }

}