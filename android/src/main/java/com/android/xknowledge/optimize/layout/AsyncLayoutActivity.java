package com.android.xknowledge.optimize.layout;

import android.os.Bundle;

import androidx.asynclayoutinflater.view.AsyncLayoutInflater;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * AsyncLayoutInflater异步解析布局优化
 * FIXME 效果通过掉帧检测，并不明显，是布局还不够复杂？？差异不明显？？
 * 参考：享学2《性能优化-布局加载优化》
 */
public class AsyncLayoutActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new AsyncLayoutInflater(this)
                .inflate(R.layout.activity_asynclayout, null, (view, resId, parent) -> setContentView(view));
    }
}