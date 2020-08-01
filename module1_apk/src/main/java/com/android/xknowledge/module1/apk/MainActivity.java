package com.android.xknowledge.module1.apk;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.xknowledge.annotation.BindPath;
import com.android.xknowledge.router.ARouter;

@BindPath("module1/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        findViewById(R.id.module1_button_module2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().jumapActivity("module2/MainActivity", null);
            }
        });
    }
}
