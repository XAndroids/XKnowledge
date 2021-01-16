package com.android.xknowledge.optimize.blockcanary;

import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

public class BlockCanaryActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blockcanary);
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}