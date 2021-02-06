package com.android.xknowledge.sdk.ui.custom.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 自定义ViewPager，覆盖onMeasure()方法，自定义ViewPager的测量方法，根据Item的尺寸计算ViewPager尺寸，已解决
 * wrap_content不生效的问题
 */
public class MyViewPager extends ViewPager {
    private static final String TAG = "MyViewPager";

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        Log.d(TAG, "onMeasure: getChildCount: " + getChildCount());
        for (int i = 0; i < getChildCount(); i++) {
            //度量孩子的尺寸
            View child = getChildAt(i);
            ViewGroup.LayoutParams lp = child.getLayoutParams();
            int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width);
            int childHightSpec = getChildMeasureSpec(heightMeasureSpec, 0, lp.height);
            child.measure(childWidthSpec, childHightSpec);

            //获取最大孩子的尺寸作为ViewPager的尺寸
            int h = child.getMeasuredHeight();
            if (h > height) {
                height = h;
            }
            Log.d(TAG, "onMeasure: " + h + " height: " + height);
        }

        //将度量的高度转换成MeasureSpec，并保存存量值
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
