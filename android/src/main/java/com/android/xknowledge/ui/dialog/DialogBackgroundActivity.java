package com.android.xknowledge.ui.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.xknowledge.R;

/**
 * 实现Dialog的背景颜色的自定义（Dim）
 * 参考：https://stackoverflow.com/questions/29227126/how-can-i-change-default-black-dim-background-color-not-the-amount-of-dim-of
 */
public class DialogBackgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_background);
        findViewById(R.id.dialogbackground_button_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundDialog dialog = new BackgroundDialog(DialogBackgroundActivity.this, R.style.Dialog_Background);
                dialog.show();
            }
        });
    }
}
