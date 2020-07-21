package com.android.xknowledge.framework.plugin;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Environment;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;
import com.android.xknowledge.plugin.core.PluginManager;
import com.android.xknowledge.plugin.core.ProxyActivity;

public class PluginActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        findViewById(R.id.plugin_button_open).setOnClickListener(v -> {
            //设置上下文
            PluginManager.getInstance().setContext(getApplicationContext());
            //加载插件，获取插件中第一个Activity信息
            PluginManager.getInstance().loadPlugin(Environment.getExternalStorageDirectory() + "/tm.apk");
            PackageInfo packageInfo = PluginManager.getInstance().getPackageInfo();
            if (packageInfo.activities.length <= 0) {
                return;
            }
            //将要真正要打开的Activity信息，通过bundler的className传递到ProxyActivity
            Intent intent = new Intent(PluginActivity.this, ProxyActivity.class);
            intent.putExtra("className", packageInfo.activities[0].name);
            startActivity(intent);
        });
    }
}
