package com.android.xknowledge.optimize.anr;

import android.os.SystemClock;

public class Util {
    public static synchronized void get() {
        SystemClock.sleep(100000);
    }
}