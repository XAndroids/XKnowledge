package com.java.xknowledge.se.thread.synchro.method;

/**
 * 取款线程
 */
public class DrawThread extends Thread {
    //模拟账户
    private final Account account;
    //当前取钱线程所希望取的钱数
    private double drawAmount;

    public DrawThread(String name, Account account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    //当多个线程涉及修改同一个共享数据（account)时，将涉及数据安全问题
    @Override
    public void run() {
        //直接调用account对象的draw()方法来执行取钱操作
        //同步方法的同步监视器是this，this代表draw()方法的对象
        //也就是说，线程进入draw()方法之前，必须先对account对象加锁
        account.draw(drawAmount);
    }
}
