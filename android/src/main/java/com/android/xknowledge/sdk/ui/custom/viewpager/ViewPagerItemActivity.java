package com.android.xknowledge.sdk.ui.custom.viewpager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.viewpager.widget.ViewPager;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ViewPager Item高度失效问题，是由于ViewPager onMeasure()计算ViewPager的时候，并没有根据Item的LayoutParams
 * 度量Item尺寸，然后根据Item高度计算ViewPager导致的
 * 参考：
 * 享学2《什么是自定义VIew，什么是高级UI》
 */
public class ViewPagerItemActivity extends TitleActivity {
    private ViewPager viewPager;
    private RadioGroup radioGroup;

    private int index;
    private int preIndex;
    private final Timer timer = new Timer();
    private final boolean isContinue = true;
    private final int itemSize = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_item);

        viewPager = findViewById(R.id.viewpageritem_myviewpager);
        radioGroup = findViewById(R.id.viewpageritem_radiogroup);

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(itemSize, this);

        viewPager.setPageMargin(30);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        viewPager.setPageTransformer(true, new PageTransform());
        initRadioButton(itemSize);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isContinue) {
                    handler.sendEmptyMessage(1);
                }
            }
        }, 2000, 2000);
    }

    private void initRadioButton(int length) {
        for (int i = 0; i < length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.viewpageitem_radiogroup_selector);
            imageView.setPadding(20, 0, 0, 0);
            radioGroup.addView(imageView, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            radioGroup.getChildAt(0).setEnabled(false);
        }
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            index = position;
            setCurrentDot(index % itemSize);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void setCurrentDot(int i) {
        if (radioGroup.getChildAt(i) != null) {
            //当前按钮不可改变
            radioGroup.getChildAt(i).setEnabled(false);
        }
        if (radioGroup.getChildAt(preIndex) != null) {
            //上个按钮可以改变
            radioGroup.getChildAt(preIndex).setEnabled(true);
            //当前位置变为上一个，继续下次轮播
            preIndex = i;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    index++;
                    viewPager.setCurrentItem(index);
            }
        }
    };
}