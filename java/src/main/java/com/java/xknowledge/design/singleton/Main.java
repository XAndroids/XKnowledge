package com.java.xknowledge.design.singleton;

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
