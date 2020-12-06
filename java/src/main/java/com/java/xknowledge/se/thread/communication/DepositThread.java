package com.java.xknowledge.se.thread.communication;

class DepositThread extends Thread {
    private Account account;
    private double depositCount;

    public DepositThread(String name, Account account, double depositCount) {
        super(name);
        this.account = account;
        this.depositCount = depositCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.deposit(depositCount);
        }
    }
}
