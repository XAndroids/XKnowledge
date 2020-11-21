package com.java.xknowledge.design.create.singleton.headfirst.dcl;

public class SingletonClient {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Singleton singleton = Singleton.getInstance();
                        System.out.println("Thread = " + Thread.currentThread().getName() + ",singleton = " + singleton.toString());
                    }
                }).start();

            }
        }
    }
}
