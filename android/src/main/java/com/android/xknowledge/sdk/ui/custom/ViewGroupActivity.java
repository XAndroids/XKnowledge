package com.android.xknowledge.sdk.ui.custom;

import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * 自定义ViewGroup
 * 参考：
 * 享学2《什么是自定义View，什么是高级UI》
 */
public class ViewGroupActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_viewgroup);
    }
}