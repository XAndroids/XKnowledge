package com.java.xknowledge.se.thread.communication;

class DrawThread extends Thread {
    private Account account;
    private double drawCount;

    public DrawThread(String name, Account account, double drawCount) {
        super(name);
        this.account = account;
        this.drawCount = drawCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.draw(drawCount);
        }
    }
}
