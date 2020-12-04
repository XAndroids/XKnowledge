package com.java.xknowledge.se.thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    //定义锁对象
    private final ReentrantLock lock = new ReentrantLock();

    private String accountNo;
    private double balance;

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //提供一个线程安全的draw()方法来完成取钱操作
    public void draw(double drawAmount) {
        //加锁
        lock.lock();
        try {
            if (balance >= drawAmount) {
                //吐出钞票
                System.out.println(accountNo + "，取钱成功，吐出钞票：" + drawAmount + ".");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance = balance - drawAmount;
                //修改余额
                System.out.println(accountNo + "，余额为：" + balance + ".");
            } else {
                //取钱失败余额
                System.out.println(accountNo + "，取钱余额不足!");
            }
        } finally {
            //修改完成释放锁
            lock.unlock();
        }

    }
}
