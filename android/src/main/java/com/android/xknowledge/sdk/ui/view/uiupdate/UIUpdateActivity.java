package com.android.xknowledge.sdk.ui.view.uiupdate;

import android.os.Bundle;
import android.widget.TextView;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * 子线程更新UI
 * 结论：
 * 在Activity.onResume(即view.assignParent(this)之前，只用异步可更新UI；
 * 在此之后，更新UI后，ReactRoomImpl会checkThread检查线程；
 * 参考：https://juejin.cn/post/6844904131136618510
 */
public class UIUpdateActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_update);
        TextView update_textview_ui = findViewById(R.id.update_textview_ui);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            update_textview_ui.setText(Thread.currentThread().getName());
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}