package com.java.xknowledge.se.thread.communication;

class Account {
    private String accountNo;
    private double balance;
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
            if (!flag) {
                wait();
            } else {
                System.out.println(Thread.currentThread().getName() + "取现:" + drawAmount);
                balance = balance - drawAmount;
                System.out.println("账户余额为:" + balance);
                flag = false;
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void deposit(double depositAccount) {
        try {
            if (flag) {
                wait();
            } else {
                System.out.println(Thread.currentThread().getName() + "存款:" + depositAccount);
                balance = balance + depositAccount;
                System.out.println("账户余额为:" + balance);
                flag = true;
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
