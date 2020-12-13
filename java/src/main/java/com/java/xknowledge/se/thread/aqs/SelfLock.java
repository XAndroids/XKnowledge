package com.java.xknowledge.se.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自己实现独占锁
 * 运行1：如果没有使用自实现独占锁，四个线程都会立即执行当前线程名称
 * Thread-0
 * Thread-3
 * Thread-2
 * Thread-1
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * Process finished with exit code 0
 * 运行2：使用自实现独占锁，四个线程线程不能同时执行，同时只有一个执行，故分别输出线程名称，并且至少间隔1秒（主线程1行）
 * Thread-0
 *
 * Thread-1
 *
 * Thread-2
 *
 * Thread-3
 *
 *
 *
 *
 *
 *
 *
 *
 * Process finished with exit code 0
 * 参考：享学2《深入理解并发编程和归纳总结01》
 */
class SelfLock implements Lock {

    //1.静态内部类，自定义同步锁，继承AbstractQueuedSynchronizer
    private static class Sync extends AbstractQueuedSynchronizer {

        //2.实现isHeldExclusively，判断锁是否是占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        //2.实现tryAcquire，获得锁
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {//CAS(0,1)
                setExclusiveOwnerThread(Thread.currentThread());//如果成功则设置自己为独占线程
                return true;
            }
            return false;
        }

        //释放锁
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);//设置独占线程为null
            setState(0);//设置锁状态为空闲
            return true;
        }

        public Condition newCondition() {
            //返回一个Condition，每个Condition都包含一个condition队列
            return new ConditionObject();
        }
    }

    //仅需要将操作代理到Sync上即可
    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }


    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    static Lock lock = new SelfLock();

    public static void main(String[] args) {
        //开启四个工作子线程异步执行
        for (int i = 0; i < 4; i++) {
            Worker worker = new Worker();
            worker.start();
        }

        //开启主线程执行
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //工作线程，打印当前线程名称，睡眠1秒
    static class Worker extends Thread {
        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
