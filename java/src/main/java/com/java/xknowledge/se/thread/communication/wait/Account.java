package com.java.xknowledge.se.thread.communication.wait;

class Account {
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

    public synchronized void draw(double drawAmount) {
        try {
            //如果flag为假，表明账户中还没有人存钱进入，取钱方法阻塞
            if (!flag) {
                wait();
            } else {
                //执行取钱操作
                System.out.println(Thread.currentThread().getName() + "取现:" + drawAmount);
                balance = balance - drawAmount;
                System.out.println("账户余额为:" + balance);
                //将标识账户是否已有存储的标记设为false
                flag = false;
                //唤醒其它线程
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void deposit(double depositAccount) {
        try {
            //如果flag为真，表明账户中已有人存钱进入，存钱方法阻塞
            if (flag) {
                wait();
            } else {
                //执行存款操作
                System.out.println(Thread.currentThread().getName() + "存款:" + depositAccount);
                balance = balance + depositAccount;
                System.out.println("账户余额为:" + balance);
                //将表示账户是否已有存款标识为true
                flag = true;
                //唤醒其它线程
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
