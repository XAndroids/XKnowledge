package com.java.xknowledge.leetcode.other;

import java.util.PriorityQueue;
import java.util.Queue;

class KthLargestTest {
    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        kthLargest.add(3);
        kthLargest.add(5);
        kthLargest.add(10);
        kthLargest.add(9);
        kthLargest.add(4);

    }

    static class KthLargest {
        private int maxIndex;
        private Queue<Integer> numQueue;

        public KthLargest(int k, int[] nums) {
            maxIndex = k;
            numQueue = new PriorityQueue<>();
            for (int i = 0; i < nums.length; i++) {
                numQueue.add(nums[i]);
            }
        }

        public int add(int val) {
            numQueue.add(val);

            int[] tempArray = new int[maxIndex - 1];
            for(int i = 0; i < maxIndex -1; i++){
                tempArray[i] = numQueue.poll();
            }

            int result = numQueue.poll();

            for(int j =0;j < tempArray.length;j++){
                numQueue.add(tempArray[j]);
            }

            return result;
        }
    }

}
