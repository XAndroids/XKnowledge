package com.java.xknowledge.se.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义实现可重入锁
 * 运行：使用可重入锁SelfReenterLock，线程递归调用可以重入自己获取的锁，不会造成死锁
 * Thread-0
 * Thread-1
 * Thread-2
 * Thread-3
 *
 * Thread-0:递归层级:3
 *
 * Thread-0:递归层级:2
 *
 * Thread-0:递归层级:1
 *
 * Thread-3:递归层级:3
 *
 * Thread-3:递归层级:2
 *
 * Thread-3:递归层级:1
 *
 * Thread-1:递归层级:3
 *
 * Thread-1:递归层级:2
 * Thread-1:递归层级:1
 *
 *
 * Thread-2:递归层级:3
 * Thread-2:递归层级:2
 * Thread-2:递归层级:1
 *
 * Process finished with exit code 0
 * 使用：不可重入锁SelfLock，则递归获取自己占有的锁，造成死锁！！
 * Thread-0
 * Thread-1
 * Thread-2
 * Thread-3
 * Thread-1:递归层级:3
 *
 *
 *
 *
 */
class SelfReenterLock implements Lock {

    //静态内部类，自定义同步器，可重入锁
    private static class Sync extends AbstractQueuedSynchronizer {
        //实现isHeldExclusively，有线程1~n次持有锁，则为占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() > 0;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                //如果CAS获取锁成功，则设置当前线程为占用线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            } else if (getExclusiveOwnerThread() == Thread.currentThread()) {
                //如果当前占有线程为自己，那么将state+1，实现锁的可重入
                setState(getState() + 1);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new IllegalMonitorStateException();
            }
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }

            //将线程状态-1
            setState(getState() - 1);
            //如果可重入锁释放锁，则将占有线程设置为null
            if (getState() == 0) {
                setExclusiveOwnerThread(null);
            }

            return true;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
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
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    static Lock selfLock = new SelfReenterLock();//使用可重入锁，则不会在递归出现该问题！！
//    static Lock selfLock = new SelfLock();//使用独占不可重入锁，由于递归调用会让自己等待自己占有的锁，造成死锁！！

    //工作线程，打印当前线程名称，睡眠1秒
    static class Worker extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reenter(3);
        }

        public void reenter(int x) {
            selfLock.lock();//如果不使用可重入锁，则线程递归依赖自己占有的锁，造成死锁！！
            try {
                System.out.println(Thread.currentThread().getName() + ":递归层级:" + x);
                TimeUnit.SECONDS.sleep(1);
                int y = x - 1;
                if (y == 0) {
                    return;
                } else {
                    reenter(y);//递归调用，重新获取同一把锁selfReenterLock，使用可重入锁
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                selfLock.unlock();
            }
        }
    }

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
}
