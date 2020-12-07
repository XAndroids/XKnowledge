package com.java.xknowledge.se.thread.communication;

/**
 * 线程通信实践-wait()/notify()/notifyAll()
 * 需求：两个线程分别代表存款者和取款者，要求存款者和取款这不断重复存款、全款动作，要求存款者将钱存入指定账户，取款者立即取
 * 出这笔钱
 * 执行：
 * 取钱者存款:600.0
 * 账户余额为:600.0
 * 存着者乙取现:600.0
 * 账户余额为:0.0
 * 取钱者存款:600.0
 * 账户余额为:600.0
 * 存着者甲取现:600.0
 * 账户余额为:0.0
 * 取钱者存款:600.0
 * 账户余额为:600.0
 * ......
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
