package com.android.xknowledge.optimize.anr;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class BackgroundService extends Service {

    //触发ANR原因2：Service处理超时
    //后台Service处理超时触发ANR模拟，20s没有处理完，即ANR
    //LogCat日志：
    //2019-12-10 16:35:55.737 1175-1209/? W/ActivityManager: Timeout executing service: ServiceRecord{15b7416 u0 com.android.xknowledge/.optimize.anr.MyService} //ANR原因执行Service超时
    // ... ...
    //2019-12-10 16:36:02.236 1175-1209/? E/ActivityManager: ANR in com.android.xknowledge
    //    PID: 23713
    //    Reason: executing service com.android.xknowledge/.optimize.anr.MyService //ANR原因是执行Service
    //    Load: 7.22 / 7.12 / 6.98
    //    CPU usage from 0ms to 6372ms later (2019-12-10 16:35:55.739 to 2019-12-10 16:36:02.110):
    //      19% 1175/system_server: 9.1% user + 10% kernel / faults: 4765 minor
    //      0.5% 904/media.swcodec: 0.1% user + 0.3% kernel / faults: 16351 minor
    //      0.1% 893/media.codec: 0% user + 0% kernel / faults: 6452 minor
    //      3.6% 23713/com.android.xknowledge: 2.8% user + 0.7% kernel / faults: 2874 minor   //没有大量的CPU运算和IO运算
    //      2.5% 1514/com.android.systemui: 1.8% user + 0.6% kernel / faults: 2531 minor
    //      2.3% 1795/com.android.phone: 1.7% user + 0.6% kernel / faults: 1118 minor
    //      0% 2443/com.google.SSRestartDetector: 0% user + 0% kernel / faults: 1262 minor
    //      1.8% 2359/com.android.nfc: 0.9% user + 0.9% kernel / faults: 1479 minor
    //      0% 2421/com.qualcomm.qti.rcsbootstraputil: 0% user + 0% kernel / faults: 1186 minor
    //      1.5% 462/logd: 0.1% user + 1.4% kernel
    //      1.5% 766/kschedfreq:0: 0% user + 1.5% kernel
    //      0% 841/media.extractor: 0% user + 0% kernel / faults: 1863 minor
    //      1.4% 7/rcu_preempt: 0% user + 1.4% kernel
    //      1.4% 773/adbd: 0.6% user + 0.7% kernel / faults: 6 minor
    //      0% 2403/com.android.ims.rcsservice: 0% user + 0% kernel / faults: 819 minor
    //      1.4% 27550/kschedfreq:2: 0% user + 1.4% kernel
    //      0% 6329/com.android.bluetooth: 0% user + 0% kernel / faults: 283 minor
    //      1.2% 22718/kworker/u8:6: 0% user + 1.2% kernel
    //      0% 2380/com.android.se: 0% user + 0% kernel / faults: 1131 minor
    //      0% 1725/com.quicinc.cne.CNEService: 0% user + 0% kernel / faults: 951 minor
    //      0% 1744/com.qualcomm.qti.telephonyservice: 0% user + 0% kernel / faults: 1016 minor
    //      0.7% 23085/kworker/u8:7: 0% user + 0.7% kernel
    //      0.6% 50/smem_native_rpm: 0% user + 0.6% kernel
    //      0.6% 54/irq/260-cpr3: 0% user + 0.6% kernel
    //      0% 913/tombstoned: 0% user + 0% kernel
    //      0.6% 22499/kworker/u8:1: 0% user + 0.6% kernel
    //      0.4% 8/rcu_sched: 0% user + 0.4% kernel
    //      0.4% 17/ksoftirqd/1: 0% user + 0.4% kernel
    //      0% 875/mediaserver: 0% user + 0% kernel / faults: 30 minor
    //      0.4% 21750/kworker/0:0: 0% user + 0.4% kernel
    //      0.4% 23630/logcat: 0% user + 0.4% kernel
    //      0.3% 3/ksoftirqd/0: 0% user + 0.3% kernel
    //      0.3% 23/ksoftirqd/2: 0% user + 0.3% kernel
    //      0.3% 583/jbd2/sda35-8: 0% user + 0.3% kernel
    //      0.3% 757/audioserver: 0% user + 0.3% kernel / faults: 48 minor
    //      0% 782/cameraserver: 0% user + 0% kernel / faults: 52 minor
    //      0% 1/init: 0% user + 0% kernel
    //      0.1% 10/rcuc/0: 0% user + 0.1% kernel
    //      0.1% 16/rcuc/1: 0% user + 0.1% kernel
    //      0.1% 22/rcuc/2: 0% user + 0.1% kernel
    //      0.1% 195/hwrng: 0% user + 0.1% kernel
    //      0.1% 494/surfaceflinger: 0.1% user + 0% kernel / faults: 28 minor
    //      0.1% 633/logcat: 0% user + 0.1% kernel
    //      0.1% 685/logcat: 0.1% user + 0% kernel
    //      0.1% 718/netd: 0% user + 0.1% kernel / faults: 11 minor
    //      0.1% 748/android.hardware.sensors@1.0-service: 0% user + 0.1% kernel
    //      0.1% 750/android.hardware.thermal@2.0-service.pixel: 0% user + 0.1% kernel
    //      0.1% 764/msm_irqbalance: 0% user + 0.1% kernel
    //      0% 785/drmserver: 0% user + 0% kernel / faults: 24 minor
    //      0.1% 884/statsd: 0.1% user + 0% kernel / faults: 26 minor
    //      0.1% 2228/irq/19-408000.q: 0% user + 0.1% kernel
    //      0.1% 21298/kworker/3:0: 0% user + 0.1% kernel
    //      0.1% 22196/kworker/1:1: 0% user + 0.1% kernel
    //      0.1% 22510/kworker/0:2: 0% user + 0.1% kernel
    //      0.1% 22807/com.topjohnwu.magisk: 0% user + 0.1% kernel / faults: 1 minor
    //     +0% 23792/crash_dump64: 0% user + 0% kernel
    //    28% TOTAL: 13% user + 13% kernel + 0.2% iowait + 0.8% irq + 0.5% softirq
    //    CPU usage from 201ms to 761ms later (2019-12-10 16:35:55.939 to 2019-12-10 16:35:56.500):
    //      53% 1175/system_server: 25% user + 28% kernel / faults: 417 minor
    //        49% 1209/ActivityManager: 25% user + 23% kernel
    //      1.7% 54/irq/260-cpr3: 0% user + 1.7% kernel
    //      2% 748/android.hardware.sensors@1.0-service: 0% user + 2% kernel
    //      3.2% 27550/kschedfreq:2: 0% user + 3.2% kernel
    //    17% TOTAL: 9.4% user + 7.6% kernel
    //traces.txt日志：
    //"main" prio=5 tid=1 Sleeping    //线程睡眠了
    //  | group="main" sCount=1 dsCount=0 flags=1 obj=0x7204c660 self=0xf5f7be00
    //  | sysTid=23713 nice=-10 cgrp=default sched=0/0 handle=0xf64c9dc0
    //  | state=S schedstat=( 3030619794 137979295 1710 ) utm=269 stm=33 core=3 HZ=100
    //  | stack=0xff390000-0xff392000 stackSize=8192KB
    //  | held mutexes=
    //  at java.lang.Thread.sleep(Native method)
    //  - sleeping on <0x06821622> (a java.lang.Object)
    //  at java.lang.Thread.sleep(Thread.java:440)
    //  - locked <0x06821622> (a java.lang.Object)
    //  at java.lang.Thread.sleep(Thread.java:356)
    //  at android.os.SystemClock.sleep(SystemClock.java:131)
    //  at com.android.xknowledge.optimize.anr.MyService.onCreate_aroundBody0(MyService.java:15)  //Java堆栈在MyService.onCreate方法中
    //  at com.android.xknowledge.optimize.anr.MyService.onCreate_aroundBody1$advice(MyService.java:23)
    //  at com.android.xknowledge.optimize.anr.MyService.onCreate(MyService.java:1)
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
        Log.i("ANR", "MyService_onCreate,thread = " + Thread.currentThread());
        //后台服务，超过20秒触发ANR
        SystemClock.sleep(30000);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
