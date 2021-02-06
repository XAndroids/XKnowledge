package com.android.xknowledge.sdk.ui.custom.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.android.xknowledge.R;

public class MyPagerAdapter extends PagerAdapter {
    private static final String TAG = "MyPagerAdapter";
    private final int mItemSize;
    private final Context mContext;

    MyPagerAdapter(int itemSize, Context context) {
        mItemSize = itemSize;
        mContext = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % mItemSize;
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewpager_linear_item, container, false);
        TextView textView = view.findViewById(R.id.tv);
        textView.setText(position + " ");
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
