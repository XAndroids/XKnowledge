package com.android.xknowledge.framework.hotfix;

import android.os.Bundle;
import android.view.View;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * Multidex热修复
 * 参考：https://www.bilibili.com/video/BV15E411o7rU
 * 问题1：确认是否打开sd卡读写权限，否则报错无法读取到文件
 * 问题2：dx命令构建dex文件，需要在包名根目录下执行，否的报错路径不对；
 */
public class MultidexActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multidex);
        findViewById(R.id.multidex_button_run).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bug.println();
            }
        });
    }
}
