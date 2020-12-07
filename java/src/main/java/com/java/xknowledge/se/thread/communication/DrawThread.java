package com.java.xknowledge.se.thread.communication;

/**
 * 存款线程
 */
class DrawThread extends Thread {
    //模拟用户账户
    private Account account;
    //当前存款线层锁希望存的钱数
    private double drawCount;

    public DrawThread(String name, Account account, double drawCount) {
        super(name);
        this.account = account;
        this.drawCount = drawCount;
    }

    @Override
    public void run() {
        //重复100次执行存款操作
        for (int i = 0; i < 100; i++) {
            account.draw(drawCount);
        }
    }
}
