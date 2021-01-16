package com.android.xknowledge.optimize.block.blockcanary;

import android.os.Looper;

/**
 * BlockCanary原理实践
 * 参考：
 * 《享学2：性能优化-卡顿和布局优化》
 */
public class BlockCanary {
    public static void install() {
        LogMonitor logMonitor = new LogMonitor();
        Looper.getMainLooper().setMessageLogging(logMonitor);
    }
}
