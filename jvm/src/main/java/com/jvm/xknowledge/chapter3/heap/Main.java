package com.jvm.xknowledge.chapter3.heap;

public class Main {
    public static void main(String[] args) {
        System.out.print("Max Memory=");
        System.out.println(Runtime.getRuntime().maxMemory() + "bytes");
        System.out.print("Free Memory=");
        System.out.println(Runtime.getRuntime().freeMemory() + "bytes");
        System.out.print("Total Memory=");
        System.out.println(Runtime.getRuntime().totalMemory() + "bytes");

        byte[] b = new byte[1 * 1024 * 1024];
        System.out.println("Allow 1 M");

        System.out.print("Max Memory=");
        System.out.println(Runtime.getRuntime().maxMemory() + "bytes");
        System.out.print("Free Memory=");
        System.out.println(Runtime.getRuntime().freeMemory() + "bytes");
        System.out.print("Total Memory=");
        System.out.println(Runtime.getRuntime().totalMemory() + "bytes");

        b = new byte[4 * 1024 * 1024];
        System.out.println("Allow 4 M");

        System.out.print("Max Memory=");
        System.out.println(Runtime.getRuntime().maxMemory() + "bytes");
        System.out.print("Free Memory=");
        System.out.println(Runtime.getRuntime().freeMemory() + "bytes");
        System.out.print("Total Memory=");
        System.out.println(Runtime.getRuntime().totalMemory() + "bytes");
    }
}
