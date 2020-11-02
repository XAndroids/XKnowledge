package com.java.xknowledge.arithmetic.string.kmp;

class KMPTest {
    public static void main(String[] args) {
        String s = "BBC ABCDAB ABCDABCDABDE";
        String p = "ABCDABD";
        KMP.kmp(s, p);
    }
}
