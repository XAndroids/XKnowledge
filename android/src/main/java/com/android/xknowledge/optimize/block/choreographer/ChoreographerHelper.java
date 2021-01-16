package com.android.xknowledge.optimize.block.choreographer;

import android.os.Build;
import android.util.Log;
import android.view.Choreographer;

/**
 * Choreographer卡顿检测
 * 参考：
 * 《享学2：性能优化-卡顿和布局优化》
 */
public class ChoreographerHelper {

    static long lastFrameTimeNanos = 0;

    public static void start() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {

                @Override
                public void doFrame(long frameTimeNanos) {
                    //上次回调时间
                    if (lastFrameTimeNanos == 0) {
                        lastFrameTimeNanos = frameTimeNanos;
                        Choreographer.getInstance().postFrameCallback(this);
                        return;
                    }
                    long diff = (frameTimeNanos - lastFrameTimeNanos) / 1_000_000;
                    if (diff > 16.6f) {
                        //掉帧数
                        int droppedCount = (int) (diff / 16.6);
                        Log.e("ChoreographerHelper", "droppedCount = " + droppedCount);
                    }
                    lastFrameTimeNanos = frameTimeNanos;
                    Choreographer.getInstance().postFrameCallback(this);
                }
            });
        }
    }
}
