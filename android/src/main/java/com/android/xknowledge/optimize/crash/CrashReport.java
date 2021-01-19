package com.android.xknowledge.optimize.crash;

import android.content.Context;

public class CrashReport {

    public static void init(Context context) {
        Context applicationContext = context.getApplicationContext();
        CrashHandler.init(applicationContext);
    }

    public static void testJavaCrash() {
        int i = 1 / 0;
    }
}
