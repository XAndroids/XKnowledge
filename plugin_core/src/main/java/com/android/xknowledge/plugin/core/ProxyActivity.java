package com.android.xknowledge.plugin.core;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class ProxyActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取到真正的目的地类名
        Intent intent = getIntent();
        String className = intent.getStringExtra("className");
        try {
            //通过类加载器去加载这个类，并且实例化这个类
            Class<?> aClass = PluginManager.getInstance().getDexClassLoader().loadClass(className);
            Object pluginActivity = aClass.newInstance();

            //判断PluginActivity是否是我们的PluginInterface类
            if (pluginActivity instanceof PluginInterface) {
                PluginInterface pluginInterface = (PluginInterface) pluginActivity;
                pluginInterface.attach(this);
                //调用插件的声明周期方法
                pluginInterface.onCreate(savedInstanceState);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getPluginResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }
}
