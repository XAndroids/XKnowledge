package com.android.xknowledge.framework.module;

import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;
import com.android.xknowledge.router.ARouter;

public class ModuleActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        findViewById(R.id.module_button_tomodule1).setOnClickListener(v -> {
            ARouter.getInstance().jumapActivity("module1/MainActivity", null);
        });

    }
}
