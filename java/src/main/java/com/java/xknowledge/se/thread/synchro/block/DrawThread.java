package com.java.xknowledge.se.thread.synchro.block;

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
        //使用acount作为作为同步监视器，任何线程进入下面同步代码块之前
        //必须先获得对account账户的锁定——其它线程无法，也就无法修改它
        //这种做法符合："加锁->修改->释放锁"的逻辑
        synchronized (account) {
            if (account.getBalance() >= drawAmount) {
                //吐出钞票
                System.out.println(account.getAccountNo() + "取钱成功，吐出钞票：" + drawAmount + ".");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                account.setBalance(account.getBalance() - drawAmount);
                //修改余额
                System.out.println(account.getAccountNo() + "余额为：" + account.getBalance() + ".");
            } else {
                //取钱失败余额
                System.out.println(account.getAccountNo() + "取钱余额不足!");
            }
        }
        //同步代码块结束，改线程释放同步锁
    }
}
