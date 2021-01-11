package com.android.xknowledge.optimize.gc;

import android.os.Bundle;
import android.view.View;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

public class GcActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gc);
        findViewById(R.id.gc_button_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 10000; i++) {
                    byte[] alloc = new byte[1024 * 1024];
                }
                System.gc();
            }
        });
    }
}