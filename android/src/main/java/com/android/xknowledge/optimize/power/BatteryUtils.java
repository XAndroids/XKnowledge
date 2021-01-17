package com.android.xknowledge.optimize.power;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;

public class BatteryUtils {

    private static final String TAG = "BatteryUtils";

    public static void addWhileList1(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        //是否处于白名单
        if (!pm.isIgnoringBatteryOptimizations(context.getPackageName())) {
            // 直接询问用户是否允许把我们应用加入白名单
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        }
    }

    public static void addWhileList2(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        //是否处于白名单
        if (!pm.isIgnoringBatteryOptimizations(context.getPackageName())) {
            //跳转到电量优化管理设置中
            context.startActivity(new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS));
        }
    }

    /**
     * 主动监测电量状态
     */
    public static void checkBattery(Context context) {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        // 是否正在充电
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        // 什么方式充电？
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        //usb
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        //充电器
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        //获得电量
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level * 100 / (float) scale;

        Log.e(TAG, "isCharging: " + isCharging + "  usbCharge: " + usbCharge + "  acCharge:" + acCharge);
        Log.e(TAG, "当前电量: " + batteryPct);
    }

    /**
     * 被动监听电量状态
     */
    public static PowerConnectionReceiver register(Context context) {
        IntentFilter ifilter = new IntentFilter();
        //充电状态
        ifilter.addAction(Intent.ACTION_POWER_CONNECTED);
        ifilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        //电量显著变化
        ifilter.addAction(Intent.ACTION_BATTERY_LOW);
        ifilter.addAction(Intent.ACTION_BATTERY_OKAY);
        PowerConnectionReceiver powerConnectionReceiver = new PowerConnectionReceiver();
        context.registerReceiver(powerConnectionReceiver, ifilter);
        return powerConnectionReceiver;
    }
}
