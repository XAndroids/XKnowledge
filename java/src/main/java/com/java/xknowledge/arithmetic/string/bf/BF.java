package com.java.xknowledge.arithmetic.string.bf;

/**
 * 字符串暴力查找算法
 * 参考：《享学1：19.字符串》
 */
class BF {
    public static void bruteForce(String s, String p) {
        int sLength = s.length();//主串长度
        int pLength = p.length();//子串长度
        if (sLength < pLength) {
            System.out.println("Error.The main string is greater than the sub string length.");
            return;
        }

        int i = 0;
        int j = 0;
        while (i < sLength && j < pLength) {
            if (s.charAt(i) == p.charAt(j)) {//判断对应位置的字符是否相等
                i++;//如果相等，则主串和子串继续依次比较
                j++;
            } else {
                i = i - j + 1;//如果不相等，主串回溯到上一次匹配的下一个字符
                j = 0;//子串从头开始重新匹配
            }
        }

        int index = -1;//成功匹配的位置
        if (j >= pLength) {//如果匹配成功
            index = i - j;
            System.out.println("Successful math index is :" + index);
        } else {
            System.out.println("Faild math");
        }
    }
}
