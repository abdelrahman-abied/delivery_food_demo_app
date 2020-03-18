package com.kira.bittaskapplication.view.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.kira.bittaskapplication.R;
import com.kira.bittaskapplication.model.Category;
import com.kira.bittaskapplication.view.adapter.CategoryRecyclerViewAdapter;
import com.kira.bittaskapplication.view.adapter.CategorySliderAdapter;
import com.kira.bittaskapplication.viewmodel.CategoryListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mCategoryViewPager;
    private CategorySliderAdapter mCategorySliderAdapter;
    private TextView[] mDots;
    private LinearLayout mDotLayout;
    RecyclerView recyclerView;
    private CategoryListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCategoryViewPager = findViewById(R.id.categoryViewPager);
        mDotLayout = findViewById(R.id.categoryDotsLaypout);
        mCategorySliderAdapter = new CategorySliderAdapter(this);
        mCategoryViewPager.setAdapter(mCategorySliderAdapter);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
        addDotsIndcator(0);
        mCategoryViewPager.addOnPageChangeListener(viewListener);
        recyclerView = findViewById(R.id.recyclerView);

       recyclerView.setLayoutManager(new GridLayoutManager(this, 2));



        recyclerView.setHasFixedSize(true);
        final CategoryRecyclerViewAdapter recyclerViewAdapter = new CategoryRecyclerViewAdapter();
        viewModel = ViewModelProviders.of(this).get(CategoryListViewModel.class);
        viewModel.getMediaData();
        viewModel.categoryMutableLiveData.observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                recyclerViewAdapter.setListItem(categories);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
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

            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }

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

//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        recyclerView.noti
//    }
}
