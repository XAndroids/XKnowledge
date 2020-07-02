package com.java.xknowledge.arithmetic;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {
    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 9};
        System.out.println(largestNumber(nums));
    }

    private static String largestNumber(int[] nums) {
        //将输入的整型转换成字符串
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        //通过自定义的比较器排序字符串
        Arrays.sort(asStrs, new LargerNumberComparator());
        System.out.println(Arrays.toString(asStrs));
        //如果，排序后，最大的数字是0，则整个数字是0。
        if (asStrs[0].equals("0")) {
            return "0";
        }

        //使用排序的结果拼接最大的字符串
        String largestNumberStr = new String();
        for (String numAsStr : asStrs) {
            largestNumberStr += numAsStr;
        }

        return largestNumberStr;
    }

    private static class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            //????传递依赖逻辑？？？
            System.out.println(order2 + "compare" + order1);
            return order2.compareTo(order1);
        }
    }
}


