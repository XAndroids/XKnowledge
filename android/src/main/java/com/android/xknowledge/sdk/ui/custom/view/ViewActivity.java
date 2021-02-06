package com.android.xknowledge.sdk.ui.custom.view;

import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

public class ViewActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }
}