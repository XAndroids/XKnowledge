package com.java.xknowledge.design.singleton;

/**
 * 单例模式，参考：https://www.bilibili.com/video/BV1tK411W7xx?p=2&spm_id_from=333.788.b_6d756c74695f70616765.2
 */
public class Main {
    public static void main(String[] args) {
//        Singleton01 singleton011 = Singleton01.getInstance();
//        Singleton01 singleton012 = Singleton01.getInstance();
//        System.out.println(singleton011 == singleton012);
//
//        for (int i = 0; i < 100; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Singleton02.getInstance().hashCode());
//                }
//            }).start();
//        }

//        for (int i = 0; i < 100; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Singleton03.getInstance().hashCode());
//                }
//            }).start();
//        }

//        for (int i = 0; i < 100; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Singleton04.getInstance().hashCode());
//                }
//            }).start();
//        }

//        for (int i = 0; i < 100; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Singleton05.getInstance().hashCode());
//                }
//            }).start();
//        }

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Singleton06.INSTANCE.hashCode());
                }
            }).start();
        }
    }
}
