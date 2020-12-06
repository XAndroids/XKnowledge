package com.java.xknowledge.se.thread.communication;

/**
 * 线程通信实践
 * 参考：《疯狂Java讲义》线程
 */
class CommunicationTest {
    public static void main(String[] args) {
        Account account = new Account("1235", 0);
        new DepositThread("取钱者", account, 600).start();
        new DrawThread("存着者甲", account, 600).start();
        new DrawThread("存着者乙", account, 600).start();
    }
}
