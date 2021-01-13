package com.java.xknowledge.se.other.referencequeue;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * ReferenceQueue实践，监控对象是否被回收了
 * 参考：享学2《性能优化-内存优化》
 */
class ReferenceQueueTest {
    public static void main(String[] args) {
        ReferenceQueue referenceQueue = new ReferenceQueue();
        Object object = new Object();
        WeakReference weakReference = new WeakReference(object, referenceQueue);

        object = null;
        Runtime.getRuntime().gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Reference findReference = null;
        do {
            findReference = referenceQueue.poll();
            System.out.println("reference = " + findReference + "是否等于上面的weakReference = "
                    + (findReference == weakReference));
        } while (findReference != null);
    }
}
