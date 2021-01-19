package com.android.xknowledge.optimize.crash;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Java Crash处理
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    private static Context mContext;

    private CrashHandler() {

    }

    public static void init(Context applicationContext) {
        mContext = applicationContext;
        //获取默认的异常处理器
        defaultUncaughtExceptionHandler =
                Thread.getDefaultUncaughtExceptionHandler();
        //设置默认的异常处理器为自定义的CrashHandler
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Log.i("CrashHandler", "thread = " + t.getName() + ", throwable = "
                + Arrays.toString(e.getStackTrace()));

        File dir = new File(mContext.getExternalCacheDir(), "crash_info");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long l = System.currentTimeMillis();
        File file = new File(dir, l + ".txt");

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            pw.println("time: xxx");
            pw.println("thread: " + t.getName());
            e.printStackTrace(pw);
            pw.flush();
            pw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            //获取信息写入文件后，执行默认异常处理器kill应用
            if (defaultUncaughtExceptionHandler != null) {
                defaultUncaughtExceptionHandler.uncaughtException(t, e);
            }
        }
    }
}
