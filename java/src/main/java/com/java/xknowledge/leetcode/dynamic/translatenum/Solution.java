package com.java.xknowledge.leetcode.dynamic.translatenum;

/**
 * 剑指 Offer 46. 把数字翻译成字符串
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 */
class Solution {
    //动态规划
    //dp[i]代表第i个字符结尾的数字翻译方案数量
    //如果第i-1和i个字符可组成两位数字，则可被翻译dp[i] = dp[i-2]+dp[i-1]，否咋dp[i]=dp[i-1]
    public int translateNum(int num) {
        String numStr = String.valueOf(num);

        //初始状态：a是dp[0]=1，b是dp[1]=1
        int a = 1, b = 1;
        //递归遍历数字字符串
        for (int i = 2; i <= numStr.length(); i++) {
            //如果i-2和i-1子串可以组成两位数翻译，则等于a+b，否则等于a
            String subStr = numStr.substring(i - 2, i);
            int c = subStr.compareTo("10") >= 0 && subStr.compareTo("25") <= 0 ? a + b : b;

            //更新dp[i-2]和dp[i-1]
            a = b;
            b = c;
        }

        return b;
    }
}
