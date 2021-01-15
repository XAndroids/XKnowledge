package com.android.xknowledge.sdk.ui.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.xknowledge.R;

public class FullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        findViewById(R.id.fullscreen_button_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenDialog dialog = new FullScreenDialog(FullScreenActivity.this, R.style.Dialog_FullScreen2);
                dialog.show();
            }
        });
    }
}