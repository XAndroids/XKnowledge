package com.java.xknowledge.leetcode.string.medium.strtoint;

/**
 * 剑指 Offer 67. 把字符串转换成整数
 * 参考：https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
 */
class Solution {
    public int strToInt(String str) {
        if (str == null || str.length() == 0) return 0;

        //字符串去除空格
        char[] numChars = str.trim().toCharArray();
        //"  "case，前面的length拦不住
        if(numChars.length == 0) return 0;

        //字符转换结果
        int result = 0;
        //数字边界判断
        int border = Integer.MAX_VALUE / 10;
        //符号位，默认正数
        int sign = 1;
        //开始遍历转换索引，默认无+号正数
        int i = 0;

        //获取符号位
        if (numChars[0] == '-') {
            //如果是负数，更新符号数，从第1位开始遍历
            sign = -1;
            i = 1;
        } else if (numChars[0] == '+') {
            //如果是+正数，从第1位开始遍历
            i = 1;
        }

        //从前往后遍历字符串
        for (int j = i; j < numChars.length; j++) {
            //如果不是数字，则break退出
            if (numChars[j] < '0' || numChars[j] > '9') break;

            //如果超过边界，即拼接前大于Integer.MAX_VALUE / 10 或者 等于Integer.MAX_VALUE / 10但num[j] >
            //'7'，因为：最大2的31次方-1，最小-2的31次方，Integer.MAX_VALUE = 2147483647
            if (result > border || (result == border && numChars[j] > '7')) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            //前面的位数*10+后面位数，数值=char-'0'转换成数字
            result = result * 10 + (numChars[j] - '0');
        }

        return sign == 1 ? result : sign * result;
    }
}
