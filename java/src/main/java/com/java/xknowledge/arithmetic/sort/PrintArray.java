package com.java.xknowledge.arithmetic.sort;

import java.util.List;

class PrintArray {
    public final static int[] SRC = {86, 11, 77, 23, 32, 45, 58, 63, 93, 4, 37, 22};

    public static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + "  ");
        }
        System.out.println("");
    }

    public static void printObject(List<Integer> array) {
        for (int i : array) {
            System.out.print(i + "  ");
        }
        System.out.println("");
    }
}
