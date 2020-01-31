package com.jvm.xknowledge.chapter3.defNew;

public class Main {
    public static void main(String[] args) {
        System.out.println("defNew Main");
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            System.out.println("allocated  = " + i + "M");
            b = new byte[1 * 1024 * 1024];
        }
    }
}
