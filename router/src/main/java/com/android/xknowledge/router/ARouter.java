package com.android.xknowledge.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

public class ARouter {
    private static ARouter aRouter = new ARouter();
    private Map<String, Class<? extends Activity>> maps;
    private Context context;

    private ARouter() {
        maps = new HashMap<>();
    }

    public static ARouter getInstance() {
        return aRouter;
    }

    public void init(Context context) {
        this.context = context;
        List<String> classNames = getClassName("com.android.xknowledge");
        for (String className : classNames) {
            try {
                Class<?> aClass = Class.forName(className);
                if (IRouter.class.isAssignableFrom(aClass)) {
                    IRouter iRouter = (IRouter) aClass.newInstance();
                    iRouter.putActivity();
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> getClassName(String packageName) {
        List<String> classList = new ArrayList<>();
        String path = null;
        try {
            path = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            DexFile dexFile = new DexFile(path);
            Enumeration entries = dexFile.entries();
            while (entries.hasMoreElements()) {
                String name = (String) entries.nextElement();
                if (name.contains(packageName)) {
                    classList.add(name);
                }
            }
        } catch (PackageManager.NameNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return classList;
    }

    public void addActivity(String key, Class<? extends Activity> clazz) {
        if (maps != null && clazz != null && !maps.containsKey(key)) {
            maps.put(key, clazz);
        }
    }

    public void jumapActivity(String key, Bundle bundle) {
        Class<? extends Activity> activityClass = maps.get(key);
        if (activityClass != null) {
            Intent intent = new Intent().setClass(context, activityClass);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
