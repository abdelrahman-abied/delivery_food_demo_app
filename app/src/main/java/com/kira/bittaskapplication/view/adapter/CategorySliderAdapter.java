package com.kira.bittaskapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.kira.bittaskapplication.R;

public class CategorySliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public CategorySliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.meat,
            R.drawable.meat,
            R.drawable.meat
    };
    @Override
    public int getCount() {
        return slide_images.length;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.category_slide_layout, container, false);
        ImageView slideImageView = view.findViewById(R.id.imageViewFood);

        slideImageView.setImageResource(slide_images[position]);

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout)object);

    }


}
