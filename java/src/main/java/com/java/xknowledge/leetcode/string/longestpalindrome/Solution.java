package com.java.xknowledge.leetcode.string.longestpalindrome;

/**
 * 5. 最长回文子串
 * 链接：https://leetcode-cn.com/problems/longest-palindrome/
 */
public class Solution {

    /**
     * 暴力方法：选出所有子字符串可能的开始和结束位置，并检验它是不是回文
     */
    private static String violenceMethod(String s) {
        int max = 0;
        String result = "";
        for (int i = 0; i <= s.length() - 1; i++) {//暴力穷举所有字符子串检测
            for (int j = i + 1; j <= s.length() - 1; j++) {
                String check = s.substring(i, j);
                if (isPalindromic(check) && check.length() > max) {//如果是回文并且长度更大
                    result = check;//更新最长回文子串
                    max = result.length();//更新最大长度
                }
            }
        }
        return result;
    }

    /**
     * 是否是回文
     */
    private static boolean isPalindromic(String check) {
        int len = check.length();
        for (int i = 0; i < len / 2; i++) {
            if (check.charAt(i) != check.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
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

    /**
     * 动态规划法：由短到长依次遍历子串判断是否是回文，长子串dp[i][j]依赖于短子串[i+1][j-1]
     */
    private static String dynamicMethod(String s) {
        System.out.println("动态规划字符串：" + s);
        //最长回文子串的left和right索引
        int len = s.length(), longestLeft = 0, longestRight = 0;

        // db[i][j] 表示字符串区间 [i, j] 是否为回文串
        boolean[][] db = new boolean[len][len];
        //右end_i为索引和终点，start_j为起点，遍历start_j至end_i的子字符串判断是否是回文
        for (int end_i = 0; end_i < len; end_i++)
            for (int start_j = 0; start_j <= end_i; start_j++) {
                //判断回文条件start_j和end_i字符相等，并且子串也是回文
                db[start_j][end_i] = (s.charAt(start_j) == s.charAt(end_i)) && (end_i - start_j < 2 || db[start_j + 1][end_i - 1]);

                //如果是回文字符串，并且比之前的回文字符串要长，更新字符串长度，记录字符串
                if (db[start_j][end_i] && end_i - start_j > longestRight - longestLeft) {
                    longestLeft = start_j;
                    longestRight = end_i;
                }
            }
        return s.substring(longestLeft, longestRight + 1);
    }

    /**
     * 中心扩展法：回文左右互为镜像，从回文中心进行展开。
     * 回文类型分为两类：
     * 1.所含字母数为奇数的回文，如："cdabafe"，中心处在某一个字符上；
     * 2.所含字符数为偶数的回文，如："rabbaf" ，中心出在两个数中间；
     */
    private static String centerMethod(String s) {
        System.out.println("中心扩展字符串：" + s);

        //定义最长回文字符串起始和末尾索引
        int longestStart = 0, longestEnd = 0;
        //以center_i为中心遍历待检测字符串
        for (int center_i = 0; center_i < s.length(); center_i++) {
            //奇数长度回文，以中心点为一个字符串，向外拓展判断回文
            int oddLength = expandAroundCenter(s, center_i, center_i);
            //偶数长度回文，以中心点为两个字符串中间，向外拓展判断回文
            int evenLength = expandAroundCenter(s, center_i, center_i + 1);
            //找出两种类型回文最大长
            int maxLongest = Math.max(oddLength, evenLength);

            //如果大于保存的最大回文长度
            if (maxLongest > longestEnd - longestStart + 1) {
                //中心+/- 1/2的回文长度，计算左右索引
                longestStart = center_i - (maxLongest - 1) / 2;
                longestEnd = center_i + maxLongest / 2;
            }
        }

        //根据最长回文左右索引截取最长回文
        return s.substring(longestStart, longestEnd + 1);
    }

    /**
     * 从startLeft和startRight开始向外扩展逐步判断是否是回文
     */
    private static int expandAroundCenter(String s, int startLeft, int startRight) {
        //如果当前字符串是回文，则继续向外拓展判断
        while (startLeft >= 0 && startRight < s.length() && s.charAt(startLeft) == s.charAt(startRight)) {
            startLeft--;
            startRight++;
        }

        //直到不满足回文条件，计算改中心点的回文长度
        return startRight - startLeft - 1;
    }

    public static void main(String[] args) {
        System.out.println(violenceMethod("a"));
//        System.out.println(invertMethod("eaaaaccccf"));
//        System.out.println(dynamicMethod("eaaaaccccf"));
//        System.out.println(centerMethod("eaaaaccccf"));
    }

}
