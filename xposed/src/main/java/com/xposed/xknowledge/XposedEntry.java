package com.xposed.xknowledge;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * XPose Module实现，实现IXposedHookLoadPackage
 * 在/assets/xposed_init文件中声明Module入口
 */
public class XposedEntry implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedBridge.log("XposedEntry handleLoadPackage");
        //向com.xposed.xknowledge.MainActivity的getTextString方法下钩子
        findAndHookMethod("com.xposed.xknowledge.MainActivity", loadPackageParam.classLoader,
                "getTextString", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log("XposedEntry beforeHookedMethodL");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {
                        XposedBridge.log("XposedEntry afterHookedMethod");
                        //Hook后，"劫持返回新的结果"
                        String result = "Xposed 被劫持了";
                        param.setResult(result);
                    }
                });
    }
}
