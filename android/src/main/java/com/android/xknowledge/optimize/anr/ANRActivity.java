package com.android.xknowledge.optimize.anr;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * ANR类型模拟：
 * 1.KeyDispatchTimeout
 * 参考：
 * 享学2《性能分析-ANR分析》
 * ANR模拟：https://juejin.cn/post/6844903802156351502#heading-9
 */
public class ANRActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anr);

        //模拟触发ANR的环境，即主线程阻塞：Sleep，死锁，for循环（CPU运算），或IO/网络/序列化（未模拟）
        findViewById(R.id.anr_button_mainsleep).setOnClickListener(v -> {
            sleepTest();
        });
        findViewById(R.id.anr_button_mainwaitblock).setOnClickListener(v -> {
            waitTest();
        });
        findViewById(R.id.anr_button_mainsynchronizedblock).setOnClickListener(v -> {
            synchronizedTest();
        });
        findViewById(R.id.anr_button_mainsynchronizedblock2).setOnClickListener(v -> {
            staticSynchronizedTest();
        });
        findViewById(R.id.anr_button_maininfinitefor).setOnClickListener(v -> {
            forTest();
        });

        //模拟触发ANR的原因：点击事件无响应，前后台Server处理超时、前后台BroadcastReceiver处理超时/ContentProvider处理超时（未模拟）
        findViewById(R.id.anr_button_keydispatchtimeout).setOnClickListener(v -> {
            //触发ANR原因1：输入事件无响应（先创造组线程阻塞），需要后续有点击事件才触发
        });

        findViewById(R.id.anr_button_backgroundservicetimeout).setOnClickListener(v -> {
            Intent intent = new Intent(ANRActivity.this, BackgroundService.class);
            startService(intent);
        });

        findViewById(R.id.anr_button_foregroundservicetimeout).setOnClickListener(v -> {
            Intent intent = new Intent(ANRActivity.this, ForegroundService.class);
            startService(intent);
        });

    }

    //造成ANR环境1：通过主线程Sleep创建，使得主线程阻塞
    //2019-12-10 15:53:30.039 1175-1419/? I/InputDispatcher: Application is not responding: Window{e11c296 u0 com.android.xknowledge/com.android.xknowledge.optimize.anr.ANRActivity}.  It has been 5007.0ms since event, 5005.7ms since wait started.  Reason: Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago.  Wait queue length: 8.  Wait queue head age: 6298.7ms.
    //触发ANR的原因：input事件处理超时
    //2019-12-10 15:53:30.040 1175-1419/? I/WindowManager: Input event dispatching timed out sending to com.android.xknowledge/com.android.xknowledge.optimize.anr.ANRActivity.  Reason: Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago.  Wait queue length: 8.  Wait queue head age: 6298.7ms.
    //2019-12-10 15:53:36.018 1175-1419/? E/ActivityManager: ANR in com.android.xknowledge (com.android.xknowledge/.optimize.anr.ANRActivity)
    //    PID: 20740
    //触发ANR的原因：input事件处理超时
    //    Reason: Input dispatching timed out (Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago.  Wait queue length: 8.  Wait queue head age: 6298.7ms.)
    //    Parent: com.android.xknowledge/.optimize.anr.ANRActivity
    //    Load: 6.84 / 6.75 / 6.51
    //    CPU usage from 1ms to 5872ms later (2019-12-10 15:53:30.096 to 2019-12-10 15:53:35.968) with 99% awake:
    //      20% 1175/system_server: 10% user + 9.8% kernel / faults: 5084 minor
    //      0% 904/media.swcodec: 0% user + 0% kernel / faults: 16351 minor
    //      5.7% 1514/com.android.systemui: 4.4% user + 1.3% kernel / faults: 2803 minor
    //      0% 893/media.codec: 0% user + 0% kernel / faults: 6452 minor
    //      3.7% 20740/com.android.xknowledge: 2.3% user + 1.3% kernel / faults: 2876 minor 3 major
    //      2.8% 2359/com.android.nfc: 1.7% user + 1.1% kernel / faults: 1552 minor
    //      0% 2380/com.android.se: 0% user + 0% kernel / faults: 873 minor
    //      1.8% 17966/kworker/u8:6: 0% user + 1.8% kernel
    //      1.7% 462/logd: 0.1% user + 1.5% kernel / faults: 1 minor
    //      1.5% 7/rcu_preempt: 0% user + 1.5% kernel
    //      1.5% 773/adbd: 0.3% user + 1.1% kernel / faults: 13 minor
    //      1.5% 1795/com.android.phone: 0.8% user + 0.6% kernel / faults: 998 minor
    //      1.3% 494/surfaceflinger: 0.6% user + 0.6% kernel / faults: 187 minor
    //      1.3% 766/kschedfreq:0: 0% user + 1.3% kernel
    //      0% 1744/com.qualcomm.qti.telephonyservice: 0% user + 0% kernel / faults: 1015 minor
    //      0% 2443/com.google.SSRestartDetector: 0% user + 0% kernel / faults: 932 minor
    //      1.3% 27550/kschedfreq:2: 0% user + 1.3% kernel
    //      1% 496/android.hardware.graphics.composer@2.1-service: 0.1% user + 0.8% kernel
    //      0% 841/media.extractor: 0% user + 0% kernel / faults: 1863 minor
    //      0% 1725/com.quicinc.cne.CNEService: 0% user + 0% kernel / faults: 832 minor
    //      0% 2421/com.qualcomm.qti.rcsbootstraputil: 0% user + 0% kernel / faults: 965 minor
    //      0.6% 50/smem_native_rpm: 0% user + 0.6% kernel
    //      0.6% 54/irq/260-cpr3: 0% user + 0.6% kernel
    //      0.6% 296/irq/480-synapti: 0% user + 0.6% kernel
    //      0% 875/mediaserver: 0% user + 0% kernel / faults: 29 minor
    //      0% 913/tombstoned: 0% user + 0% kernel
    //      0% 2403/com.android.ims.rcsservice: 0% user + 0% kernel / faults: 907 minor
    //      0.6% 17948/kworker/0:3: 0% user + 0.6% kernel / faults: 3 minor
    //      0.6% 20369/kworker/u8:5: 0% user + 0.6% kernel
    //      0.5% 8/rcu_sched: 0% user + 0.5% kernel
    //      0.5% 17/ksoftirqd/1: 0% user + 0.5% kernel
    //      0.5% 23/ksoftirqd/2: 0% user + 0.5% kernel
    //      0.5% 742/android.hardware.health@2.0-service.marlin: 0.1% user + 0.3% kernel
    //      0.3% 3/ksoftirqd/0: 0% user + 0.3% kernel
    //      0.3% 729/android.system.suspend@1.0-service: 0% user + 0.3% kernel
    //      0% 782/cameraserver: 0% user + 0% kernel / faults: 55 minor
    //      0.3% 884/statsd: 0.1% user + 0.1% kernel / faults: 28 minor
    //      0% 6329/com.android.bluetooth: 0% user + 0% kernel / faults: 710 minor
    //      0.3% 19460/kworker/u8:3: 0% user + 0.3% kernel
    //      0.3% 20342/kworker/1:0: 0% user + 0.3% kernel
    //      0.3% 20826/logcat: 0% user + 0.3% kernel
    //      0% 1/init: 0% user + 0% kernel
    //      0.1% 10/rcuc/0: 0% user + 0.1% kernel
    //      0.1% 16/rcuc/1: 0% user + 0.1% kernel
    //      0.1% 22/rcuc/2: 0% user + 0.1% kernel
    //      0% 387/irq/206-usbin-u: 0% user + 0% kernel
    //      0.1% 455/ueventd: 0.1% user + 0% kernel
    //      0.1% 583/jbd2/sda35-8: 0% user + 0.1% kernel
    //      0.1% 633/logcat: 0% user + 0.1% kernel
    //      0.1% 685/logcat: 0.1% user + 0% kernel
    //      0.1% 699/logcat: 0% user + 0.1% kernel
    //      0.1% 730/healthd: 0% user + 0.1% kernel
    //      0.1% 748/android.hardware.sensors@1.0-service: 0% user + 0.1% kernel
    //      0.1% 755/thermal-engine: 0% user + 0.1% kernel
    //      0.1% 764/msm_irqbalance: 0% user + 0.1% kernel
    //      0% 872/media.metrics: 0% user + 0% kernel / faults: 25 minor
    //      0.1% 2228/irq/19-408000.q: 0% user + 0.1% kernel
    //      0.1% 19589/kworker/2:0: 0% user + 0.1% kernel
    //      0.1% 19732/kworker/0:1: 0% user + 0.1% kernel / faults: 2 minor
    //      0.1% 19737/mdss_fb0: 0% user + 0.1% kernel
    //      0.1% 19908/kworker/1:1: 0% user + 0.1% kernel
    //     +0% 20830/crash_dump64: 0% user + 0% kernel
    //tracex.txt日志：
    //"main" prio=5 tid=1 Sleeping    //主线程Sleep状态
    //  | group="main" sCount=1 dsCount=0 flags=1 obj=0x7204c660 self=0xf5f7be00
    //  | sysTid=20887 nice=-10 cgrp=default sched=0/0 handle=0xf64c9dc0
    //  | state=S schedstat=( 17800292465 5010757455 16629 ) utm=1305 stm=474 core=3 HZ=100
    //  | stack=0xff390000-0xff392000 stackSize=8192KB
    //  | held mutexes=
    //  at java.lang.Thread.sleep(Native method)
    //  - sleeping on <0x0cc9e567> (a java.lang.Object)
    //  at java.lang.Thread.sleep(Thread.java:440)
    //  - locked <0x0cc9e567> (a java.lang.Object)
    //  at java.lang.Thread.sleep(Thread.java:356)
    //  at android.os.SystemClock.sleep(SystemClock.java:131)
    //  at com.android.xknowledge.optimize.anr.ANRActivity.sleepTest(ANRActivity.java:53)    //sleepTest执行Sleep造成主线程阻塞触发ANR
    //  at com.android.xknowledge.optimize.anr.ANRActivity.lambda$onCreate$0$ANRActivity(ANRActivity.java:22)
    //  at com.android.xknowledge.optimize.anr.-$$Lambda$ANRActivity$yHw6qN4wxsU-Qij1U6RdXjijVTI.onClick(lambda:-1)
    //  at android.view.View.performClick(View.java:7140)
    //  at android.view.View.performClickInternal(View.java:7117)
    //  at android.view.View.access$3500(View.java:801)
    //  at android.view.View$PerformClick.run(View.java:27351)
    //  at android.os.Handler.handleCallback(Handler.java:883)
    //  at android.os.Handler.dispatchMessage(Handler.java:100)
    //  at android.os.Looper.loop(Looper.java:214)
    //  at android.app.ActivityThread.main(ActivityThread.java:7356)
    //  at java.lang.reflect.Method.invoke(Native method)
    //  at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
    //  at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:930)
    public void sleepTest() {
        SystemClock.sleep(100000);
    }

    //造成ANR环境2：通过主线程死锁，使得主线程阻塞
    //LogCat日志：触发ANR的是输入事件无响应，但不是ANR发生原因是死锁
    //2019-12-10 15:31:39.830 1175-1419/? I/InputDispatcher: Application is not responding: Window{8d7688 u0 com.android.xknowledge/com.android.xknowledge.optimize.anr.ANRActivity}.  It has been 5007.7ms since event, 5006.2ms since wait started.  Reason: Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago.  Wait queue length: 8.  Wait queue head age: 5623.6ms.
    //2019-12-10 15:31:39.835 1175-1419/? I/WindowManager: Input event dispatching timed out sending to com.android.xknowledge/com.android.xknowledge.optimize.anr.ANRActivity.  Reason: Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago.  Wait queue length: 8.  Wait queue head age: 5623.6ms.
    //2019-12-10 15:31:46.033 1175-1419/? E/ActivityManager: ANR in com.android.xknowledge (com.android.xknowledge/.optimize.anr.ANRActivity)
    //    PID: 19931
    //    Reason: Input dispatching timed out (Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago.  Wait queue length: 8.  Wait queue head age: 5623.6ms.)
    //    Parent: com.android.xknowledge/.optimize.anr.ANRActivity
    //    Load: 6.6 / 6.34 / 6.35
    //    CPU usage from 0ms to 6013ms later (2019-12-10 15:31:39.973 to 2019-12-10 15:31:45.985):
    //      21% 1175/system_server: 10% user + 11% kernel / faults: 4994 minor 2 major
    //      1.9% 904/media.swcodec: 0.6% user + 1.3% kernel / faults: 16351 minor
    //      0.7% 893/media.codec: 0.4% user + 0.2% kernel / faults: 6452 minor
    //      3% 1514/com.android.systemui: 2% user + 1% kernel / faults: 3225 minor
    //      2.1% 19931/com.android.xknowledge: 1.6% user + 0.5% kernel / faults: 2963 minor
    //      0.2% 841/media.extractor: 0% user + 0.1% kernel / faults: 1863 minor
    //      0.2% 1744/com.qualcomm.qti.telephonyservice: 0.1% user + 0.1% kernel / faults: 914 minor
    //      0.2% 2403/com.android.ims.rcsservice: 0.1% user + 0.1% kernel / faults: 951 minor
    //      1.8% 1795/com.android.phone: 1.1% user + 0.6% kernel / faults: 950 minor
    //      1.6% 462/logd: 0.5% user + 1.1% kernel
    //      1.6% 766/kschedfreq:0: 0% user + 1.6% kernel
    //      0.2% 2359/com.android.nfc: 0.1% user + 0.1% kernel / faults: 1508 minor
    //      0.2% 2443/com.google.SSRestartDetector: 0.1% user + 0% kernel / faults: 940 minor
    //      1.4% 7/rcu_preempt: 0% user + 1.4% kernel
    //      1.5% 773/adbd: 0.3% user + 1.1% kernel / faults: 1 minor
    //      1.5% 19460/kworker/u8:3: 0% user + 1.5% kernel
    //      1.3% 27550/kschedfreq:2: 0% user + 1.3% kernel
    //      1.1% 17948/kworker/0:3: 0% user + 1.1% kernel
    //      0.1% 1725/com.quicinc.cne.CNEService: 0% user + 0% kernel / faults: 948 minor
    //      0.1% 2421/com.qualcomm.qti.rcsbootstraputil: 0% user + 0% kernel / faults: 887 minor
    //      1% 19730/kworker/u8:1: 0% user + 1% kernel
    //      0.8% 54/irq/260-cpr3: 0% user + 0.8% kernel
    //      0.6% 50/smem_native_rpm: 0% user + 0.6% kernel
    //      0% 875/mediaserver: 0% user + 0% kernel / faults: 72 minor
    //      0% 913/tombstoned: 0% user + 0% kernel
    //      0% 2380/com.android.se: 0% user + 0% kernel / faults: 943 minor
    //      0.4% 3/ksoftirqd/0: 0% user + 0.4% kernel
    //      0.4% 8/rcu_sched: 0% user + 0.4% kernel
    //      0% 892/wificond: 0% user + 0% kernel / faults: 28 minor
    //      0.5% 17966/kworker/u8:6: 0% user + 0.5% kernel
    //      0.3% 17/ksoftirqd/1: 0% user + 0.3% kernel
    //      0.3% 23/ksoftirqd/2: 0% user + 0.3% kernel
    //      0.3% 583/jbd2/sda35-8: 0% user + 0.3% kernel
    //      0.3% 754/android.hardware.wifi@1.0-service: 0.3% user + 0% kernel / faults: 5 minor
    //      0.3% 884/statsd: 0.1% user + 0.1% kernel / faults: 29 minor
    //      0.3% 1998/VosMCThread: 0% user + 0.3% kernel
    //      0.3% 2088/com.google.android.gms.persistent: 0.1% user + 0.1% kernel / faults: 38 minor
    //      0.3% 20014/logcat: 0.1% user + 0.1% kernel
    //      0% 1/init: 0% user + 0% kernel
    //      0.1% 22/rcuc/2: 0% user + 0.1% kernel
    //      0.1% 276/spi6: 0% user + 0.1% kernel
    //      0.1% 357/nanohub: 0% user + 0.1% kernel
    //      0% 473/vold: 0% user + 0% kernel / faults: 55 minor
    //      0.1% 494/surfaceflinger: 0% user + 0.1% kernel / faults: 26 minor
    //      0.1% 633/logcat: 0% user + 0.1% kernel
    //      0.1% 685/logcat: 0.1% user + 0% kernel
    //      0.1% 699/logcat: 0.1% user + 0% kernel
    //      0.1% 729/android.system.suspend@1.0-service: 0.1% user + 0% kernel
    //      0.1% 748/android.hardware.sensors@1.0-service: 0.1% user + 0% kernel
    //      0.1% 755/thermal-engine: 0% user + 0.1% kernel
    //      0.1% 764/msm_irqbalance: 0% user + 0.1% kernel
    //      0% 782/cameraserver: 0% user + 0% kernel / faults: 124 minor
    //      0% 785/drmserver: 0% user + 0% kernel / faults: 21 minor
    //      0% 840/mediadrmserver: 0% user + 0% kernel / faults: 19 minor
    //      0% 872/media.metrics: 0% user + 0% kernel / faults: 103 minor
    //      0% 1235/lowi-server: 0% user + 0% kernel
    //      0.1% 2228/irq/19-408000.q: 0% user + 0.1% kernel
    //      0.1% 17954/kworker/1:2: 0% user + 0.1% kernel
    //      0.1% 19589/kworker/2:0: 0% user + 0.1% kernel
    //      0.1% 19906/kworker/0:4: 0% user + 0.1% kernel
    //     +0% 20032/crash_dump64: 0% user + 0% kernel
    //     +0% 20078/crash_dump64: 0% user + 0% kernel
    //     +0% 2
    //traces.txt日志：
    //"main" prio=5 tid=1 Waiting //线程wait，等待锁状态
    //  | group="main" sCount=1 dsCount=0 flags=1 obj=0x7204c660 self=0xf5f7be00
    //  | sysTid=19931 nice=-10 cgrp=default sched=0/0 handle=0xf64c9dc0
    //  | state=S schedstat=( 3254823925 192630317 1293 ) utm=287 stm=37 core=3 HZ=100
    //  | stack=0xff390000-0xff392000 stackSize=8192KB
    //  | held mutexes=
    //  at java.lang.Object.wait(Native method)
    //  - waiting on <0x0c4d4d4d> (a java.lang.String)
    //  at java.lang.Object.wait(Object.java:442)
    //  at java.lang.Object.wait(Object.java:568)
    //  at com.android.xknowledge.optimize.anr.ANRActivity.waitTest(ANRActivity.java:56)//崩溃堆栈，是造成主线程阻塞的地方，而不是触发ANR的地方
    //  - locked <0x0c4d4d4d> (a java.lang.String)
    //  at com.android.xknowledge.optimize.anr.ANRActivity.lambda$onCreate$1$ANRActivity(ANRActivity.java:26)
    //  at com.android.xknowledge.optimize.anr.-$$Lambda$ANRActivity$Vnz8vYQinz0m8TGZ41TQrmASnos.onClick(lambda:-1)
    //  at android.view.View.performClick(View.java:7140)
    //  at android.view.View.performClickInternal(View.java:7117)
    //  at android.view.View.access$3500(View.java:801)
    //  at android.view.View$PerformClick.run(View.java:27351)
    //  at android.os.Handler.handleCallback(Handler.java:883)
    //  at android.os.Handler.dispatchMessage(Handler.java:100)
    //  at android.os.Looper.loop(Looper.java:214)
    //  at android.app.ActivityThread.main(ActivityThread.java:7356)
    //  at java.lang.reflect.Method.invoke(Native method)
    //  at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
    //  at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:930)
    public void waitTest() {
        String s = "";
        synchronized (s) {
            try {
                s.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //造成ANR环境3：通过主线程和子线程竞争锁死锁，使得主线程阻塞
    //Logcat日志：
    //2019-12-10 15:57:56.430 1175-1419/? I/InputDispatcher: Application is not responding: Window{55850ab u0 com.android.xknowledge/com.android.xknowledge.optimize.anr.ANRActivity}.  It has been 5007.1ms since event, 5006.3ms since wait started.  Reason: Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago.  Wait queue length: 6.  Wait queue head age: 5566.2ms.
    //2019-12-10 15:57:56.433 1175-1419/? I/WindowManager: Input event dispatching timed out sending to com.android.xknowledge/com.android.xknowledge.optimize.anr.ANRActivity.  Reason: Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago.  Wait queue length: 6.  Wait queue head age: 5566.2ms.
    //2019-12-10 15:58:02.875 1175-1419/? E/ActivityManager: ANR in com.android.xknowledge (com.android.xknowledge/.optimize.anr.ANRActivity)
    //    PID: 21160
    //    Reason: Input dispatching timed out (Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago.  Wait queue length: 6.  Wait queue head age: 5566.2ms.)
    //    Parent: com.android.xknowledge/.optimize.anr.ANRActivity
    //    Load: 6.92 / 7.02 / 6.71
    //    CPU usage from 0ms to 6246ms later (2019-12-10 15:57:56.574 to 2019-12-10 15:58:02.821) with 99% awake:
    //      21% 1175/system_server: 11% user + 9.3% kernel / faults: 4934 minor
    //      0.7% 904/media.swcodec: 0.2% user + 0.4% kernel / faults: 16351 minor
    //      0.2% 893/media.codec: 0.1% user + 0% kernel / faults: 6452 minor
    //      3.3% 1514/com.android.systemui: 2.5% user + 0.8% kernel / faults: 2862 minor
    //      2.2% 21160/com.android.xknowledge: 1.7% user + 0.4% kernel / faults: 2807 minor
    //      0% 2359/com.android.nfc: 0% user + 0% kernel / faults: 1508 minor
    //      1.9% 462/logd: 0.4% user + 1.4% kernel
    //      0% 2421/com.qualcomm.qti.rcsbootstraputil: 0% user + 0% kernel / faults: 836 minor
    //      1.6% 766/kschedfreq:0: 0% user + 1.6% kernel
    //      1.6% 20346/kworker/u8:1: 0% user + 1.6% kernel
    //      1.4% 7/rcu_preempt: 0% user + 1.4% kernel
    //      1.4% 773/adbd: 0.3% user + 1.1% kernel / faults: 10 minor
    //      0% 1795/com.android.phone: 0% user + 0% kernel / faults: 999 minor
    //      0% 2403/com.android.ims.rcsservice: 0% user + 0% kernel / faults: 885 minor
    //      0% 841/media.extractor: 0% user + 0% kernel / faults: 1863 minor
    //      0% 1725/com.quicinc.cne.CNEService: 0% user + 0% kernel / faults: 996 minor
    //      1.2% 27550/kschedfreq:2: 0% user + 1.2% kernel
    //      0% 6329/com.android.bluetooth: 0% user + 0% kernel / faults: 107 minor
    //      0% 1744/com.qualcomm.qti.telephonyservice: 0% user + 0% kernel / faults: 984 minor
    //      0% 2443/com.google.SSRestartDetector: 0% user + 0% kernel / faults: 878 minor
    //      0.8% 50/smem_native_rpm: 0% user + 0.8% kernel
    //      0% 2380/com.android.se: 0% user + 0% kernel / faults: 832 minor
    //      0.6% 54/irq/260-cpr3: 0% user + 0.6% kernel
    //      0.6% 494/surfaceflinger: 0.1% user + 0.4% kernel / faults: 186 minor
    //      0% 913/tombstoned: 0% user + 0% kernel
    //      0% 19460/kworker/u8:3: 0% user + 0% kernel
    //      0.6% 19732/kworker/0:1: 0% user + 0.6% kernel / faults: 6 minor
    //      0.6% 20369/kworker/u8:5: 0% user + 0.6% kernel
    //      0.4% 3/ksoftirqd/0: 0% user + 0.4% kernel
    //      0.4% 8/rcu_sched: 0% user + 0.4% kernel
    //      0.4% 764/msm_irqbalance: 0.1% user + 0.3% kernel
    //      0.4% 21493/logcat: 0.1% user + 0.3% kernel
    //      0% 1/init: 0% user + 0% kernel
    //      0.3% 17/ksoftirqd/1: 0% user + 0.3% kernel
    //      0.3% 23/ksoftirqd/2: 0% user + 0.3% kernel
    //      0.3% 757/audioserver: 0% user + 0.3% kernel / faults: 70 minor
    //      0% 875/mediaserver: 0% user + 0% kernel / faults: 30 minor
    //      0.1% 10/rcuc/0: 0% user + 0.1% kernel
    //      0.1% 16/rcuc/1: 0% user + 0.1% kernel
    //      0% 22/rcuc/2: 0% user + 0% kernel
    //      0.1% 245/kgsl_worker_thr: 0% user + 0.1% kernel
    //      0.1% 296/irq/480-synapti: 0% user + 0.1% kernel
    //      0.1% 357/nanohub: 0% user + 0.1% kernel
    //      0% 473/vold: 0% user + 0% kernel / faults: 28 minor
    //      0.1% 496/android.hardware.graphics.composer@2.1-service: 0% user + 0.1% kernel
    //      0.1% 583/jbd2/sda35-8: 0% user + 0.1% kernel
    //      0.1% 685/logcat: 0% user + 0.1% kernel
    //      0% 699/logcat: 0% user + 0% kernel
    //      0% 782/cameraserver: 0% user + 0% kernel / faults: 51 minor
    //      0% 785/drmserver: 0% user + 0% kernel / faults: 21 minor
    //      0% 840/mediadrmserver: 0% user + 0% kernel / faults: 53 minor
    //      0% 872/media.metrics: 0% user + 0% kernel / faults: 27 minor
    //      0.1% 884/statsd: 0% user + 0.1% kernel / faults: 27 minor
    //      0.1% 2228/irq/19-408000.q: 0% user + 0.1% kernel
    //      0.1% 17948/kworker/0:3: 0% user + 0.1% kernel
    //      0.1% 19737/mdss_fb0: 0% user + 0.1% kernel
    //      0.1% 19908/kworker/1:1: 0% user + 0.1% kernel
    //      0.1% 21298/kworker/3:0: 0% user + 0.1% kernel
    //     +0% 21497/crash_dump64: 0% user + 0% kernel
    //     +0% 21541/crash_dump64: 0% user + 0% kernel
    //     +0% 21543/com.android.bluetooth: 0% user + 0% kernel
    //    29% TOTAL: 14% user + 13% kernel + 0.2% iowait + 0.7% irq + 0.3% softirq
    //    CPU usage from 102ms to 667ms later (20
    //traces.txt日志：
    //"main" prio=5 tid=1 Blocked  //主线程死锁，Block
    //  | group="main" sCount=1 dsCount=0 flags=1 obj=0x7204c660 self=0xf5f7be00
    //  | sysTid=21160 nice=-10 cgrp=default sched=0/0 handle=0xf64c9dc0
    //  | state=S schedstat=( 17198515665 637497498 8532 ) utm=1214 stm=505 core=3 HZ=100
    //  | stack=0xff390000-0xff392000 stackSize=8192KB
    //  | held mutexes=
    //  at com.android.xknowledge.optimize.anr.ANRActivity.synchronizedInMain(ANRActivity.java:-1)//synchronizedInMain发生死锁，
    //  - waiting to lock <0x0f8a169b> (a com.android.xknowledge.optimize.anr.ANRActivity) held by thread 4 // held by thread 4 持有锁
    //  at com.android.xknowledge.optimize.anr.-$$Lambda$FlhR06NRaFHVEVObK1F3uX_K8C4.run(lambda:-1)
    //  at android.app.Activity.runOnUiThread(Activity.java:6904)
    //  at com.android.xknowledge.optimize.anr.ANRActivity.synchronizedTest(ANRActivity.java:169)
    //  at com.android.xknowledge.optimize.anr.ANRActivity.lambda$onCreate$2$ANRActivity(ANRActivity.java:30)
    //  at com.android.xknowledge.optimize.anr.-$$Lambda$ANRActivity$hCUZmR8Fh3-J8fWEPP-tXWD7Zgg.onClick(lambda:-1)
    //  at android.view.View.performClick(View.java:7140)
    //  at android.view.View.performClickInternal(View.java:7117)
    //  at android.view.View.access$3500(View.java:801)
    //  at android.view.View$PerformClick.run(View.java:27351)
    //  at android.os.Handler.handleCallback(Handler.java:883)
    //  at android.os.Handler.dispatchMessage(Handler.java:100)
    //  at android.os.Looper.loop(Looper.java:214)
    //  at android.app.ActivityThread.main(ActivityThread.java:7356)
    //  at java.lang.reflect.Method.invoke(Native method)
    //  at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
    //  at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:930)
    //... ...
    //"Thread-3" prio=5 tid=4 Sleeping
    //  | group="main" sCount=1 dsCount=0 flags=1 obj=0x132c0100 self=0xf5f7da00
    //  | sysTid=21478 nice=0 cgrp=default sched=0/0 handle=0xca152230
    //  | state=S schedstat=( 361562 132604 2 ) utm=0 stm=0 core=3 HZ=100
    //  | stack=0xca04f000-0xca051000 stackSize=1040KB
    //  | held mutexes=
    //  at java.lang.Thread.sleep(Native method)
    //  - sleeping on <0x0e80b75c> (a java.lang.Object)
    //  at java.lang.Thread.sleep(Thread.java:440)
    //  - locked <0x0e80b75c> (a java.lang.Object)
    //  at java.lang.Thread.sleep(Thread.java:356)
    //  at android.os.SystemClock.sleep(SystemClock.java:131)
    //  at com.android.xknowledge.optimize.anr.ANRActivity.synchronizedInThread(ANRActivity.java:173)//第一次点击，线程3执行synchronizedInThread持有锁，Sleep状态
    //  - locked <0x0f8a169b> (a com.android.xknowledge.optimize.anr.ANRActivity)
    //  at com.android.xknowledge.optimize.anr.-$$Lambda$sO3TJO9XcNMGXn8gvyL9vkosG9Y.run(lambda:-1)
    //  at java.lang.Thread.run(Thread.java:919)
    //
    //"Thread-4" prio=5 tid=5 Blocked
    //  | group="main" sCount=1 dsCount=0 flags=1 obj=0x133127c0 self=0xf5f7e800
    //  | sysTid=21481 nice=0 cgrp=default sched=0/0 handle=0xca049230
    //  | state=S schedstat=( 181615 97500 2 ) utm=0 stm=0 core=3 HZ=100
    //  | stack=0xc9f46000-0xc9f48000 stackSize=1040KB
    //  | held mutexes=
    //  at com.android.xknowledge.optimize.anr.ANRActivity.synchronizedInThread(ANRActivity.java:-1)//第二次点击，线程4执行synchronizedInThread，无法获取锁，Blocked状态
    //  - waiting to lock <0x0f8a169b> (a com.android.xknowledge.optimize.anr.ANRActivity) held by thread 4
    //  at com.android.xknowledge.optimize.anr.-$$Lambda$sO3TJO9XcNMGXn8gvyL9vkosG9Y.run(lambda:-1)
    //  at java.lang.Thread.run(Thread.java:919)
    public void synchronizedTest() {
        //注意：第一次点击创建Thread-3获取syn锁，并且Sleep 30秒，Main线程想获取锁Block阻塞
        //第二次点击：Thread-4也无法获取到锁故Blocked阻塞
        new Thread(this::synchronizedInThread).start();
        runOnUiThread(this::synchronizedInMain);
    }

    public synchronized void synchronizedInThread() {
        SystemClock.sleep(30000);
    }

    public synchronized void synchronizedInMain() {
    }

    //造成ANR环境4：通过主线程和子线程竞争锁死锁，使得主线程阻塞
    public void staticSynchronizedTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Util.get();
            }
        }).start();
        Util.get();
    }

    //造成ANR环境5：通过无限for循环大量CPU操作，是的主线程阻塞
    //LogCat日志：
    //2019-12-10 16:08:12.086 1175-1419/? I/InputDispatcher: Application is not responding: Window{d41c818 u0 com.android.xknowledge/com.android.xknowledge.optimize.anr.ANRActivity}.  It has been 5006.7ms since event, 5005.5ms since wait started.  Reason: Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago.  Wait queue length: 6.  Wait queue head age: 6183.3ms.
    //2019-12-10 16:08:12.089 1175-1419/? I/WindowManager: Input event dispatching timed out sending to com.android.xknowledge/com.android.xknowledge.optimize.anr.ANRActivity.  Reason: Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago.  Wait queue length: 6.  Wait queue head age: 6183.3ms.
    //2019-12-10 16:08:17.406 1175-1419/? E/ActivityManager: ANR in com.android.xknowledge (com.android.xknowledge/.optimize.anr.ANRActivity)
    //    PID: 21553
    //    Reason: Input dispatching timed out (Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago.  Wait queue length: 6.  Wait queue head age: 6183.3ms.)
    //    Parent: com.android.xknowledge/.optimize.anr.ANRActivity
    //    Load: 6.63 / 6.78 / 6.73
    //    CPU usage from 0ms to 5120ms later (2019-12-10 16:08:12.221 to 2019-12-10 16:08:17.341):
    //      102% 21553/com.android.xknowledge: 100% user + 1.5% kernel / faults: 2915 minor  //无限for循环，造成应用进程用户空间CPU 100%，点击事件无法响应！！！
    //      25% 1175/system_server: 13% user + 11% kernel / faults: 5386 minor
    //      0.1% 904/media.swcodec: 0% user + 0% kernel / faults: 16351 minor
    //      0% 893/media.codec: 0% user + 0% kernel / faults: 6452 minor
    //      3.1% 1514/com.android.systemui: 2.1% user + 0.9% kernel / faults: 2448 minor
    //      2.1% 462/logd: 0.7% user + 1.3% kernel
    //      0% 2421/com.qualcomm.qti.rcsbootstraputil: 0% user + 0% kernel / faults: 836 minor
    //      1.9% 494/surfaceflinger: 0.5% user + 1.3% kernel / faults: 66 minor
    //      1.9% 773/adbd: 0.3% user + 1.5% kernel / faults: 14 minor
    //      0% 2359/com.android.nfc: 0% user + 0% kernel / faults: 1490 minor
    //      1.7% 1795/com.android.phone: 0.9% user + 0.7% kernel / faults: 1050 minor
    //      1.3% 7/rcu_preempt: 0% user + 1.3% kernel
    //      1.3% 496/android.hardware.graphics.composer@2.1-service: 0.3% user + 0.9% kernel
    //      1.3% 19460/kworker/u8:3: 0% user + 1.3% kernel
    //      1.1% 766/kschedfreq:0: 0% user + 1.1% kernel
    //      0% 841/media.extractor: 0% user + 0% kernel / faults: 1863 minor
    //      0% 1725/com.quicinc.cne.CNEService: 0% user + 0% kernel / faults: 917 minor
    //      0% 1744/com.qualcomm.qti.telephonyservice: 0% user + 0% kernel / faults: 985 minor
    //      0% 2380/com.android.se: 0% user + 0% kernel / faults: 971 minor
    //      0% 2443/com.google.SSRestartDetector: 0% user + 0% kernel / faults: 819 minor
    //      0% 2403/com.android.ims.rcsservice: 0% user + 0% kernel / faults: 859 minor
    //      0% 8/rcu_sched: 0% user + 0% kernel
    //      0% 757/audioserver: 0% user + 0% kernel / faults: 106 minor
    //      0% 875/mediaserver: 0% user + 0% kernel / faults: 29 minor
    //      0% 913/tombstoned: 0% user + 0% kernel
    //      0.5% 21750/kworker/0:0: 0% user + 0.5% kernel / faults: 4 minor
    //      0.3% 3/ksoftirqd/0: 0% user + 0.3% kernel
    //      0.3% 23/ksoftirqd/2: 0% user + 0.3% kernel
    //      0.3% 50/smem_native_rpm: 0% user + 0.3% kernel
    //      0% 785/drmserver: 0% user + 0% kernel / faults: 57 minor
    //      0.3% 20369/kworker/u8:5: 0% user + 0.3% kernel
    //      0.3% 21784/kworker/u8:6: 0% user + 0.3% kernel
    //      0% 1/init: 0% user + 0% kernel
    //      0.1% 17/ksoftirqd/1: 0% user + 0.1% kernel
    //      0% 22/rcuc/2: 0% user + 0% kernel
    //      0% 473/vold: 0% user + 0% kernel / faults: 28 minor
    //      0% 583/jbd2/sda35-8: 0% user + 0% kernel
    //      0% 633/logcat: 0% user + 0% kernel
    //      0.1% 732/android.hardware.bluetooth@1.0-service-qti: 0% user + 0.1% kernel
    //      0% 782/cameraserver: 0% user + 0% kernel / faults: 57 minor
    //      0% 872/media.metrics: 0% user + 0% kernel / faults: 27 minor
    //      0.1% 2228/irq/19-408000.q: 0% user + 0.1% kernel
    //      0.1% 19737/mdss_fb0: 0% user + 0.1% kernel
    //      0% 21090/kworker/2:2: 0% user + 0% kernel
    //      0% 21298/kworker/3:0: 0% user + 0% kernel
    //      0.1% 21817/logcat: 0% user + 0.1% kernel
    //     +0% 21821/crash_dump64: 0% user + 0% kernel
    //     +0% 21869/kworker/2:1: 0% user + 0% kernel
    //    54% TOTAL: 40% user + 13% kernel + 0.4% iowait + 0.7% irq + 0.1% softirq
    //    CPU usage from 106ms to 653ms later (2019-12-10 16:08:12.328 to 2019-12-10 16:08:12.875):
    //      97% 21553/com.android.xknowledge: 97% user + 0% kernel
    //        97% 21553/roid.xknowledge: 97% user + 0% kernel
    //      63% 1175/system_server: 42% user + 21% kernel / faults: 674 minor
    //        42% 1419/InputDispatcher: 23% user + 18% kernel
    //        21% 1188/HeapTaskDaemon: 18% user + 2.6% kernel
    //      4.3% 494/surfaceflinger: 2.1% user + 2.1% kernel
    //        2.1% 546/HwBinder:494_1: 0% user + 2.1% kernel
    //      1.8% 22/rcuc/2: 0% user + 1.8% kernel
    //      2.1% 496/android.hardware.graphics.composer@2.1-service: 0% user + 2.1% kernel
    //        2.1% 511/SDM_EventThread: 0% user + 2.1% kernel
    //      3.6% 19460/kworker/u8:3: 0% user + 3.6% kernel
    //    51% TOTAL
    //traces.txt日志：
    //"main" prio=5 tid=1 Native
    //  | group="main" sCount=1 dsCount=0 flags=1 obj=0x72547d98 self=0x72df13e000
    //  | sysTid=1175 nice=-2 cgrp=default sched=0/0 handle=0x72e065ced0
    //  | state=S schedstat=( 44344565358 32640221994 91829 ) utm=3271 stm=1162 core=1 HZ=100
    //  | stack=0x7fceda8000-0x7fcedaa000 stackSize=8192KB
    //  | held mutexes=
    //  kernel: (couldn't read /proc/self/task/1175/stack)
    //  native: #00 pc 00000000000ced58  /apex/com.android.runtime/lib64/bionic/libc.so (__epoll_pwait+8)
    //  native: #01 pc 0000000000017c30  /system/lib64/libutils.so (android::Looper::pollInner(int)+148)
    //  native: #02 pc 0000000000017afc  /system/lib64/libutils.so (android::Looper::pollOnce(int, int*, int*, void**)+56)
    //  native: #03 pc 0000000000136da4  /system/lib64/libandroid_runtime.so (android::android_os_MessageQueue_nativePollOnce(_JNIEnv*, _jobject*, long, int)+44)
    //  at android.os.MessageQueue.nativePollOnce(Native method)
    //  at android.os.MessageQueue.next(MessageQueue.java:336)
    //  at android.os.Looper.loop(Looper.java:174)
    //  at com.android.server.SystemServer.run(SystemServer.java:541)
    //  at com.android.server.SystemServer.main(SystemServer.java:349)
    //  at java.lang.reflect.Method.invoke(Native method)
    //  at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
    //  at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:908)
    public void forTest() {
        for (; ; ) {
        }
    }
}