package com.java.xknowledge.arithmetic.string.bf;

/**
 * 字符串暴力查找
 */
class BFTest {
    public static void main(String[] args) {
        String s = "BBC ABCDAB ABCDABCDABDE";
        String p = "ABCDABD";
        BF.bruteForce(s, p);
    }
}
