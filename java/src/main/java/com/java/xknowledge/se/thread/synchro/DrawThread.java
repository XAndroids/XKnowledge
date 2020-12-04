package com.java.xknowledge.se.thread.synchro;

/**
 * 取款线程
 */
public class DrawThread extends Thread {
    //模拟账户
    private Account account;
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
}
