package com.java.xknowledge.se.thread.synchro1;

/**
 * 线程安全问题：银行取钱
 * 多个线程共同访问Account账户，先后取现修改当前金额，造成"脏数据"-线程安全为
 * 使用synchronized(account)同步代码块，实现线程同步
 */
class DrawTest {
    public static void main(String[] args) {
        Account account = new Account("0001", 1000);
        new DrawThread("甲", account, 600).start();
        new DrawThread("乙", account, 600).start();
    }
}
