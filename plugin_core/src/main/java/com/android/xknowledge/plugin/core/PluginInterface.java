package com.android.xknowledge.plugin.core;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * 标准，让所有的插件apk里面的Actiity都实现这个接口
 * 所有外置apk中的app页面必须实现这个接口类，这个接口类规范所有外置apk大的activity标准，面向接口开发
 */
public interface PluginInterface {
    //注入上下文
    void attach(Activity alipayActivity);
    //模拟生命周期
    void onCreate(Bundle savedInstanceState);
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();

    void onSaveInstanceState(Bundle outState);
    boolean onTouchEvent(MotionEvent event);
    void onBackPressed();
}
