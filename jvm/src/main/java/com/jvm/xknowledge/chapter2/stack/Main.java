package com.jvm.xknowledge.chapter2.stack;

public class Main {
    private static int count = 0;

    public static void resursion(long a, long b, long c) {
        long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
        count++;
        resursion(a, b, c);
    }

    public static void main(String[] args) {
        try {
            resursion(0L, 0L, 0L);
        } catch (Throwable e) {
            System.out.println("deep of calling = " + count);
            e.printStackTrace();
        }
    }
}
