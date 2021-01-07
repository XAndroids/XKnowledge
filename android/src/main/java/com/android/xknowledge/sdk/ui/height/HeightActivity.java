package com.android.xknowledge.sdk.ui.height;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.android.xknowledge.R;

/**
 * 获取应用高度和应用实际高度，差别是否包含系统的状态栏、导航栏相关高度
 * 全面屏+沉浸式：
 * 2021-01-07 15:49:52.343 29376-29376/com.android.xknowledge I/HeightActivity: getHeight1 = 2400
 * 2021-01-07 15:49:52.344 29376-29376/com.android.xknowledge I/HeightActivity: getHeight2 = 2400
 * 2021-01-07 15:49:52.344 29376-29376/com.android.xknowledge I/HeightActivity: getHeight3 = 2400
 * 2021-01-07 15:49:52.344 29376-29376/com.android.xknowledge I/HeightActivity: getHeight4 = 2400
 * 2021-01-07 15:49:52.344 29376-29376/com.android.xknowledge I/HeightActivity: getHeight5 = 2400
 * 2021-01-07 15:49:52.345 29376-29376/com.android.xknowledge I/HeightActivity: getHeight6 = 2400
 * 2021-01-07 15:49:52.345 29376-29376/com.android.xknowledge I/HeightActivity: getHeight7 = 2400
 * 全面屏+沉浸式+导航栏:
 * 2021-01-07 15:49:07.448 29376-29376/com.android.xknowledge I/HeightActivity: getHeight1 = 2282
 * 2021-01-07 15:49:07.449 29376-29376/com.android.xknowledge I/HeightActivity: getHeight2 = 2282
 * 2021-01-07 15:49:07.449 29376-29376/com.android.xknowledge I/HeightActivity: getHeight3 = 2282
 * 2021-01-07 15:49:07.449 29376-29376/com.android.xknowledge I/HeightActivity: getHeight4 = 2282
 * 2021-01-07 15:49:07.450 29376-29376/com.android.xknowledge I/HeightActivity: getHeight5 = 2400
 * 2021-01-07 15:49:07.450 29376-29376/com.android.xknowledge I/HeightActivity: getHeight6 = 2282
 * 2021-01-07 15:49:07.450 29376-29376/com.android.xknowledge I/HeightActivity: getHeight7 = 2400
 * 参考：https://www.jianshu.com/p/1a931d943fb4
 */
public class HeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);
        Log.i("HeightActivity", "getHeight1 = " + getHeight1());
        Log.i("HeightActivity", "getHeight2 = " + getHeight2());
        Log.i("HeightActivity", "getHeight3 = " + getHeight3());
        Log.i("HeightActivity", "getHeight4 = " + getHeight4());
        Log.i("HeightActivity", "getHeight5 = " + getHeight5());
        Log.i("HeightActivity", "getHeight6 = " + getHeight6());
        Log.i("HeightActivity", "getHeight7 = " + getHeight7());
        Log.i("HeightActivity", "getStatusBarHeight = " + getStatusBarHeight(this));

    }

    public int getHeight1() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public int getHeight2() {
        //方法0
        return getWindowManager().getDefaultDisplay().getHeight();
    }

    //应用显示区域
    public int getHeight3() {
        //方法1
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        return point.y;
    }

    //应用显示区域
    public int getHeight4() {
        //方法2
        Rect rect = new Rect();
        getWindowManager().getDefaultDisplay().getRectSize(rect);
        return rect.bottom;
    }

    //应用实际显示区域（包含系统装饰区域）
    public int getHeight5() {
        //方法4
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(point);
        return point.y;
    }

    //应用显示区域
    public int getHeight6() {
        //方法3
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    //应用实际显示区域（包含系统装饰区域）
    public int getHeight7() {
        //方法5
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelOffset(resId);
        }
        return result;
    }
}