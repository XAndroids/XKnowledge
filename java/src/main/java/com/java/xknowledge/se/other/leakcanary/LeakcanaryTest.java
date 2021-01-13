package com.java.xknowledge.se.other.leakcanary;

/**
 * LeakCanary原理模拟实现
 * 参考：享学2《性能优化-内存优化》
 */
public class LeakcanaryTest {

    public static void main(String[] args) {
        Watcher watcher = new Watcher();

        Object obj = new Object();
        System.out.println("obj: " + obj);
        watcher.watch(obj, "");

        Utils.sleep(500);
        //释放对象
        obj = null;
        Utils.gc();
        //TODO: 思考如何判断被观察的对象可能存在泄漏嫌疑
        Utils.sleep(5000);
        System.out.println("查看是否在怀疑列表：" + watcher.getRetainedReferences().size());
    }

}
