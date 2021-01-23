package com.android.xknowledge.optimize.anr;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.android.xknowledge.R;

public class ForegroundService extends Service {
    //触发ANR原因3：前台服务，200s为处理完。FIXME 实际测试并不是？？，如下面日志，约20s
    //2019-12-10 17:01:21.480 24784-24784/com.android.xknowledge I/TAG: -------> onCreate asm success : ANRActivity
    //... ...
    //2019-12-10 17:01:42.671 1175-1209/? W/ActivityManager: Timeout executing service: ServiceRecord{e44f7b1 u0 com.android.xknowledge/.optimize.anr.ForegroundService} //ANR原因，执行Service超时
    //... ...
    //2019-12-10 17:01:48.667 1175-1209/? E/ActivityManager: ANR in com.android.xknowledge
    //    PID: 24784
    //    Reason: executing service com.android.xknowledge/.optimize.anr.ForegroundService //ANR原因，执行ForegroundService
    //    Load: 7.18 / 6.99 / 6.86
    //    CPU usage from 0ms to 5919ms later (2019-12-10 17:01:42.674 to 2019-12-10 17:01:48.592):
    //      19% 1175/system_server: 8.8% user + 11% kernel / faults: 4874 minor
    //      0.3% 904/media.swcodec: 0.1% user + 0.2% kernel / faults: 16351 minor
    //      0% 893/media.codec: 0% user + 0% kernel / faults: 6452 minor
    //      4.1% 1514/com.android.systemui: 3% user + 1% kernel / faults: 3297 minor
    //      0% 2443/com.google.SSRestartDetector: 0% user + 0% kernel / faults: 1200 minor
    //      2.2% 24784/com.android.xknowledge: 1.7% user + 0.5% kernel / faults: 2870 minor
    //      1.8% 462/logd: 0.5% user + 1.3% kernel
    //      0% 841/media.extractor: 0% user + 0% kernel / faults: 1879 minor
    //      1.8% 1795/com.android.phone: 1% user + 0.8% kernel / faults: 1187 minor
    //      1.7% 2359/com.android.nfc: 0.8% user + 0.8% kernel / faults: 1727 minor
    //      1.5% 766/kschedfreq:0: 0% user + 1.5% kernel
    //      1.5% 773/adbd: 0.3% user + 1.1% kernel / faults: 13 minor
    //      1.5% 24019/kworker/u8:3: 0% user + 1.5% kernel
    //      1.3% 7/rcu_preempt: 0% user + 1.3% kernel
    //      0% 2421/com.qualcomm.qti.rcsbootstraputil: 0% user + 0% kernel / faults: 1142 minor
    //      1.3% 27550/kschedfreq:2: 0% user + 1.3% kernel
    //      0% 1744/com.qualcomm.qti.telephonyservice: 0% user + 0% kernel / faults: 1271 minor
    //      0% 1725/com.quicinc.cne.CNEService: 0% user + 0% kernel / faults: 1143 minor
    //      0% 2380/com.android.se: 0% user + 0% kernel / faults: 1133 minor
    //      0% 2403/com.android.ims.rcsservice: 0% user + 0% kernel / faults: 1170 minor
    //      0.8% 24536/kworker/u8:6: 0% user + 0.8% kernel
    //      0.6% 50/smem_native_rpm: 0% user + 0.6% kernel
    //      0.6% 24087/kworker/0:4: 0% user + 0.6% kernel
    //      0% 8/rcu_sched: 0% user + 0% kernel
    //      0.5% 17/ksoftirqd/1: 0% user + 0.5% kernel
    //      0% 757/audioserver: 0% user + 0% kernel / faults: 172 minor
    //      0% 875/mediaserver: 0% user + 0% kernel / faults: 30 minor
    //      0% 913/tombstoned: 0% user + 0% kernel
    //      0.5% 24737/logcat: 0.1% user + 0.3% kernel
    //      0.3% 3/ksoftirqd/0: 0% user + 0.3% kernel
    //      0.3% 23/ksoftirqd/2: 0% user + 0.3% kernel
    //      0.3% 54/irq/260-cpr3: 0% user + 0.3% kernel
    //      0% 782/cameraserver: 0% user + 0% kernel / faults: 71 minor
    //      0.3% 2228/irq/19-408000.q: 0% user + 0.3% kernel
    //      0.3% 24066/kworker/u8:5: 0% user + 0.3% kernel
    //      0% 1/init: 0% user + 0% kernel / faults: 2 minor
    //      0.1% 16/rcuc/1: 0% user + 0.1% kernel
    //      0.1% 22/rcuc/2: 0% user + 0.1% kernel
    //      0% 473/vold: 0% user + 0% kernel / faults: 37 minor
    //      0.1% 494/surfaceflinger: 0% user + 0.1% kernel / faults: 25 minor
    //      0.1% 583/jbd2/sda35-8: 0% user + 0.1% kernel
    //      0.1% 633/logcat: 0% user + 0.1% kernel
    //      0.1% 685/logcat: 0.1% user + 0% kernel
    //      0.1% 699/logcat: 0.1% user + 0% kernel
    //      0.1% 755/thermal-engine: 0% user + 0.1% kernel
    //      0.1% 764/msm_irqbalance: 0% user + 0.1% kernel
    //      0% 785/drmserver: 0% user + 0% kernel / faults: 27 minor
    //      0% 872/media.metrics: 0% user + 0% kernel / faults: 26 minor
    //      0.1% 884/statsd: 0% user + 0.1% kernel / faults: 27 minor
    //      0% 1315/kworker/1:1H: 0% user + 0% kernel
    //      0.1% 1998/VosMCThread: 0% user + 0.1% kernel
    //      0% 22518/kworker/2:3: 0% user + 0% kernel
    //      0.1% 24040/kworker/1:3: 0% user + 0.1% kernel
    //      0% 24736/tty_worker_thre: 0% user + 0% kernel
    //     +0% 24934/crash_dump64: 0% user + 0% kernel
    //    28% TOTAL: 13% user + 13% kernel + 0.3% iowait + 0.5% irq + 0.1% softirq
    //    CPU usage from 180ms to 752ms later (2019-12-10 17:01:42.854 to 2019-12-10 17:01:43.425):
    //      51% 1175/system_server: 25% user + 25% kernel / faults: 426 minor
    //        51% 1209/ActivityManager: 25% user + 25% kernel
    //      1.7% 7/rcu_preempt: 0% user + 1.7% kernel
    //      1.7% 50/smem_native_rpm: 0% user + 1.7% kernel
    //      3.1% 24019/kworker/u8:3: 0% user + 3.1% kernel
    //      3.2% 24536/kworker/u8:6: 0% user + 3.2% kernel
    //    17% TOTAL: 8% user + 8.8% kernel + 0.4% iowait + 0.4% irq
    //traces.txt日志：
    //"main" prio=5 tid=1 Sleeping  //主线程睡眠
    //  | group="main" sCount=1 dsCount=0 flags=1 obj=0x7204c660 self=0xf5f7be00
    //  | sysTid=24784 nice=-10 cgrp=default sched=0/0 handle=0xf64c9dc0
    //  | state=S schedstat=( 8332016407 1089740195 5056 ) utm=660 stm=172 core=2 HZ=100
    //  | stack=0xff390000-0xff392000 stackSize=8192KB
    //  | held mutexes=
    //  at java.lang.Thread.sleep(Native method)
    //  - sleeping on <0x0734a0f3> (a java.lang.Object)
    //  at java.lang.Thread.sleep(Thread.java:440)
    //  - locked <0x0734a0f3> (a java.lang.Object)
    //  at java.lang.Thread.sleep(Thread.java:356)
    //  at android.os.SystemClock.sleep(SystemClock.java:131)
    //  at com.android.xknowledge.optimize.anr.ForegroundService.onCreate_aroundBody0(ForegroundService.java:20)  //ForegroundService onCreate()堆栈
    //  at com.android.xknowledge.optimize.anr.ForegroundService.onCreate_aroundBody1$advice(ForegroundService.java:23)
    //  at com.android.xknowledge.optimize.anr.ForegroundService.onCreate(ForegroundService.java:1)
    //  at android.app.ActivityThread.handleCreateService(ActivityThread.java:3953)
    //  at android.app.ActivityThread.access$1500(ActivityThread.java:219)
    //  at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1875)
    //  at android.os.Handler.dispatchMessage(Handler.java:107)
    //  at android.os.Looper.loop(Looper.java:214)
    //  at android.app.ActivityThread.main(ActivityThread.java:7356)
    //  at java.lang.reflect.Method.invoke(Native method)
    //  at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
    //  at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:930)
    @Override
    public void onCreate() {
        super.onCreate();
        showNotification();
        //前台服务，超过200s才会触发ANR
        SystemClock.sleep(300000);
    }

    /**
     * 启动前台通知
     */
    private void showNotification() {
        //创建通知详细信息
        Notification.Builder mBuilder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("2016年11月24日")
                .setContentText("今天天气阴天，8到14度");
        //创建点击跳转Intent
        Intent intent = new Intent(this, ANRActivity.class);
        //创建任务栈Builder
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ANRActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        //设置跳转Intent到通知中
        mBuilder.setContentIntent(pendingIntent);
        //获取通知服务
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //构建通知
        Notification notification = mBuilder.build();
        //显示通知
        nm.notify(0, notification);
        //启动为前台服务
        startForeground(0, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
