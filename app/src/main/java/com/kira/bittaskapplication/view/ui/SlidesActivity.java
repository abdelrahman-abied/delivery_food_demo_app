package com.kira.bittaskapplication.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kira.bittaskapplication.R;
import com.kira.bittaskapplication.view.adapter.SliderAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class SlidesActivity extends AppCompatActivity {

    private ViewPager mSliderViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    Handler handler;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    Button getStartedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slides);
        handler = new Handler();
        mSliderViewPager = findViewById(R.id.viewPager);
        mDotLayout = findViewById(R.id.linearLayout);
        sliderAdapter = new SliderAdapter(this);
        mSliderViewPager.setAdapter(sliderAdapter);
        getStartedBtn = findViewById(R.id.getStartBtn);
        addDotsIndcator(0);
        mSliderViewPager.addOnPageChangeListener(viewListener);
        /*After setting the adapter we use timer to autoslide*/
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                mSliderViewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTOHomePage();
            }
        });
    }

    public void addDotsIndcator(int position) {
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(40);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mDotLayout.addView(mDots[i]);
        }
        int i = mDots.length - 1;
        if (mDots.length > 0) {

            mDots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
        if (position == i) {

            Runnable r = new Runnable() {
                public void run() {
                    moveTOHomePage();
                }
            };
            handler.postDelayed(r, 3000);


        }
    }

    private void moveTOHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndcator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
