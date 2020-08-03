package com.android.xknowledge.ui.event.ontouch;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * View事件分发机制
 * 参考：https://www.bilibili.com/video/BV1mE411j7YN?t=6171
 * https://juejin.im/post/6844904061410476039#heading-12
 */
public class ViewEventActivity extends TitleActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewevent);
        button = findViewById(R.id.ontouch_button_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("OnTouchActivity","onClick");
            }
        });

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("OnTouchActivity","onTouch");
                return false;
            }
        });
    }
}
