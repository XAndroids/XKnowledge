package com.android.xknowledge.plugin.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 加载插件的核心类：
 * 1.获取到插件的资源对象；
 * 2.获取插件的类加载器；
 * 3.获取插件的包信息类
 * 参考：https://www.bilibili.com/video/BV1Kg4y1z7jt
 */
public class PluginManager {
    private static PluginManager pluginManager = new PluginManager();
    //上下文
    private Context context;
    //插件资源对象
    private Resources pluginResources;
    //插件类加载器
    private DexClassLoader dexClassLoader;
    //插件的包信息类
    private PackageInfo packageInfo;

    public void setContext(Context context) {
        this.context = context;
    }

    private PluginManager() { }

    public static PluginManager getInstance() {
        return pluginManager;
    }

    /**
     * 去加载插件apk
     *
     * @param pluginPath 加载插件的存储路径
     */
    public void loadPlugin(String pluginPath) {
        //获取到包管理器
        PackageManager packageManager = context.getPackageManager();
        //获取插件的包信息类
        packageInfo = packageManager.getPackageArchiveInfo(pluginPath, PackageManager.GET_ACTIVITIES);

        //获取插件解压后的目录
        File pluginFile = context.getDir("plugin", Context.MODE_PRIVATE);
        //获取到插件的类加载器
        dexClassLoader = new DexClassLoader(pluginPath, pluginFile.getAbsolutePath(), null,
                context.getClassLoader());

        //获取插件资源对象
        try {
            //创建一个AssetManager对象
            AssetManager assetManager = AssetManager.class.newInstance();
            //拿到它里面的addAssetPath的方法，并执行指定assetManager管理的apk资源
            Method addAssetPath = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, pluginPath);
            pluginResources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Resources getPluginResources() {
        return pluginResources;
    }

    DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }
}
