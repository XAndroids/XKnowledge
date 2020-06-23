package com.java.xknowledge.arithmetic;

/**
 * 题目：
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例1:
 * 输入: "babad" 输出: "bab" 注意: "aba" 也是一个有效答案。
 * 示例2:
 * 输入: "cbbd" 输出: "bb"
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(violenceMethod("eaaaaccccf"));
        System.out.println(invertMethod("eaaaaccccf"));
    }

    /**
     * 暴力方法：选出所有子字符串可能的开始和结束位置，并检验它是不是回文
     */
    private static String violenceMethod(String s) {
        System.out.println("暴力校验字符串：" + s);
        //初始化回文字符串的起始位置和最大长度
        int violenceStart = 0;
        int violenceMaxLength = 1;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                System.out.println("校验字符子串：" + s.substring(i, j + 1));

                //从字符串"头尾"逐步向内，判断是否是回文字符串
                int checkStart = i, checkEnd = j;
                while (checkStart < checkEnd && s.charAt(checkStart) == s.charAt(checkEnd)) {
                    checkStart++;
                    checkEnd--;
                }
                int checkLength = j - i + 1;
                //如果checkStart和checkEnd，"相交"或者"相叉"则是回文，分别"aabaa"：checkStart = checkEnd，
                //"abba"：checkStart > checkEnd
                if (checkStart >= checkEnd) {
                    System.out.println("校验回文字符子串：" + s.substring(i, j + 1) + "!");
                    if (checkLength > violenceMaxLength) {
                        //记录更长回文起始位置和长度
                        violenceMaxLength = checkLength;
                        violenceStart = i;
                        System.out.println("校验更长回文字符子串：" + s.substring(violenceStart, violenceStart + violenceMaxLength) + "!!");
                    }

                }
            }
        }

        //根据最长回文长度和位置，返回最长回文
        return s.substring(violenceStart, violenceStart + violenceMaxLength);
    }

    /**
     * 反转方法：反转 S，使之变成 S′。找到 S 和 S′之间最长的公共子串。
     * 注意：
     * 需要检查子串的索引是否与反向子串的原始索引相同，如: S = “abacdfgdcaba” , S′ = “abacdgfdcaba”，
     * S以及 S' 之间的最长公共子串为 “abacd”。显然，这不是回文。
     */
    static String invertMethod(String s) {
        System.out.println("反转校验字符串：" + s);
        String longestString = s.substring(s.length() - 1);

        //反转查找字符串
        String invertString = new StringBuilder(s).reverse().toString();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                //遍历原字符串的子串，由于回文的镜像型，则在反转字符串中存在
                //如：S = “abacccc” , S′ = “ccccaba”，aba在反转串中存在
                //并且检查子串和反转检查子串在原字符串索引相同，则是回文
                //如：S = “abacdfgdcaba” , S′ = “abacdgfdcaba”，反转包含子串"abacd"，但不是回文
                String checkString = s.substring(i, j + 1);
                System.out.println("校验字符子串：" + checkString);
                if (invertString.contains(checkString) && s.indexOf(checkString) ==
                        s.indexOf(new StringBuilder(checkString).reverse().toString())) {
                    System.out.println("校验回文字符子串：" + checkString + "!");
                    //如果
                    if (checkString.length() > longestString.length()) {
                        longestString = checkString;
                        System.out.println("校验更长回文字符子串：" + longestString + "!!");
                    }
                }
            }
        }

        return longestString;
    }
}
