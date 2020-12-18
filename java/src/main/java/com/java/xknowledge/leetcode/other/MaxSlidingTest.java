package com.java.xknowledge.leetcode.other;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class MaxSlidingTest {
    private static final int numSize = 100000000;

    public static void main(String[] args) {
        int[] nums = new int[numSize];
        for (int i = 0; i < numSize; i++) {
            nums[i] = i;
        }
        long start = System.currentTimeMillis();
        maxSlidingWindow(nums, 3);
        System.out.println("maxSlidingWindow = " + (System.currentTimeMillis() - start));

        long start2 = System.currentTimeMillis();
        maxSlidingWindow2(nums, 3);
        System.out.println("maxSlidingWindow2 = " + (System.currentTimeMillis() - start2));
    }

    /**
     * 该算法时间法则度logn2
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] kMaxArray = new int[nums.length - k + 1];
        Queue<Integer> kPriorityQueue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < nums.length - k; i++) {
            for (int j = i; j < i + k; j++) {
                kPriorityQueue.add(nums[j]);
            }
            kMaxArray[i] = kPriorityQueue.peek();
            kPriorityQueue.clear();
        }
        return kMaxArray;
    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int[] kMaxArray = new int[nums.length - k + 1];
        Queue<Integer> kPriorityQueue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            kPriorityQueue.add(nums[i]);
            if (kPriorityQueue.size() >= k) {
                kMaxArray[i - k + 1] = kPriorityQueue.peek();
                kPriorityQueue.remove(nums[i - k + 1]);
            }
        }
        return kMaxArray;
    }
}
