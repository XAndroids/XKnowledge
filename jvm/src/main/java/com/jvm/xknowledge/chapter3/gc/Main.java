package com.jvm.xknowledge.chapter3.gc;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            System.out.println("Hello Workd" + i);
            System.gc();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
