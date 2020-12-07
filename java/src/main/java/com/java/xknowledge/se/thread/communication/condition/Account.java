package com.java.xknowledge.se.thread.communication.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    //显示定义Lock对象
    private final Lock lock = new ReentrantLock();
    //获取指定lock对象对应的condition
    private final Condition condition = lock.newCondition();

    //封装账户编号、账户余额
    private String accountNo;
    private double balance;
    //账户中是否有存款的旗标
    private boolean flag = false;

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void draw(double drawAmount) {
        lock.lock();//替代synchronized加锁
        try {
            //如果flag为假，表明账户中还没有人存钱进入，取钱方法阻塞
            if (!flag) {
                condition.await();//使用lock对应的condition.wait()阻塞当前线程
            } else {
                //执行取钱操作
                System.out.println(Thread.currentThread().getName() + "取现:" + drawAmount);
                balance = balance - drawAmount;
                System.out.println("账户余额为:" + balance);
                //将标识账户是否已有存储的标记设为false
                flag = false;
                //唤醒其它线程
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//使用finally块来释放锁
        }
    }

    public void deposit(double depositAccount) {
        lock.lock();
        try {
            //如果flag为真，表明账户中已有人存钱进入，存钱方法阻塞
            if (flag) {
                condition.await();
            } else {
                //执行存款操作
                System.out.println(Thread.currentThread().getName() + "存款:" + depositAccount);
                balance = balance + depositAccount;
                System.out.println("账户余额为:" + balance);
                //将表示账户是否已有存款标识为true
                flag = true;
                //唤醒其它线程
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
