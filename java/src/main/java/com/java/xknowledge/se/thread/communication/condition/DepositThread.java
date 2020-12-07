package com.java.xknowledge.se.thread.communication.condition;

/**
 * 取款线程
 */
class DepositThread extends Thread {
    //模拟用户账户
    private Account account;
    //当前取款线层锁希望取的钱数
    private double depositCount;

    public DepositThread(String name, Account account, double depositCount) {
        super(name);
        this.account = account;
        this.depositCount = depositCount;
    }

    @Override
    public void run() {
        //重复100次执行取款操作
        for (int i = 0; i < 100; i++) {
            account.deposit(depositCount);
        }
    }
}
