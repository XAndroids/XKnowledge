package com.java.xknowledge.leetcode.other.fib;

/**
 * 509. 斐波那契数
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项
 * 数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n) 。
 * 示例 1：
 * <p>
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * 示例 2：
 * <p>
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * 示例 3：
 * <p>
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 * <p>
 * 提示：
 * 0 <= n <= 30
 * 参考：https://leetcode-cn.com/problems/fibonacci-number
 */
class Solution {
    /**
     * 递归方案：
     * 存在f(0),f(1),f(2)等重复运算；
     * 时间复杂度：O(n∧2);
     * 运行：
     * run fib1(20)
     * run fib1(19)
     * run fib1(18)
     * run fib1(17)
     * run fib1(16)
     * run fib1(15)
     * run fib1(14)
     * run fib1(13)
     * run fib1(12)
     * run fib1(11)
     * run fib1(10)
     * run fib1(9)
     * run fib1(8)
     * run fib1(7)
     * run fib1(6)
     * run fib1(5)
     * run fib1(4)
     * run fib1(3)
     * run fib1(2)
     * run fib1(1)
     * run fib1(0)
     * ... ...
     * run fib1(1)
     * run fib1(2)
     * run fib1(1)
     * run fib1(0)
     * fib1 = 6765,time = 200
     * Process finished with exit code 0
     */
    public static void main0(String[] args) {
        long start = System.currentTimeMillis();
        int fib1 = fib1(20);
        System.out.print("fib1 = " + fib1 + ",time = " + (System.currentTimeMillis() - start));
    }

    public static int fib1(int n) {
        System.out.println("run fib1(" + n + ")");
        return n <= 1 ? n : fib1(n - 1) + fib1(n - 2);
    }

    /**
     * 递归+记忆方案：保证每个fib2(n)只计算一次
     * 时间复杂度：O(n);
     * run fib2(20)
     * run fib2(19)
     * run fib2(18)
     * run fib2(17)
     * run fib2(16)
     * run fib2(15)
     * run fib2(14)
     * run fib2(13)
     * run fib2(12)
     * run fib2(11)
     * run fib2(10)
     * run fib2(9)
     * run fib2(8)
     * run fib2(7)
     * run fib2(6)
     * run fib2(5)
     * run fib2(4)
     * run fib2(3)
     * run fib2(2)
     * fib2 = 6765,time = 8
     * Process finished with exit code 0
     */
    public static void main1(String[] args) {
        long start = System.currentTimeMillis();
        int n = 20;
        int fib2 = fib2(n, new int[n]);
        System.out.print("fib2 = " + fib2 + ",time = " + (System.currentTimeMillis() - start));
    }

    private static int fib2(int n, int[] memory) {
        if (n <= 1) {
            return n;
        }

        if (memory[n - 1] == 0) {
            System.out.println("run fib2(" + n + ")");
            memory[n - 1] = fib2(n - 1, memory) + fib2(n - 2, memory);
        }
        return memory[n - 1];
    }

    /**
     * 动态规划方案1：
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 运行：
     * run fib3(20)
     * fib3 = 6765,time = 2
     * Process finished with exit code 0
     * 参考：https://leetcode-cn.com/problems/fibonacci-number/solution/509-fei-bo-na-qi-shu-dong-tai-gui-hua-ru-nuyx/
     */
    public static void main3(String[] args) {
        long start = System.currentTimeMillis();
        int fib3 = fib3(20);
        System.out.print("fib3 = " + fib3 + ",time = " + (System.currentTimeMillis() - start));
    }

    private static int fib3(int n) {
        if (n <= 1) {
            return n;
        }

        System.out.println("run fib3(" + n + ")");
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 动态规划方案2：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 运行：
     * run fib4(20)
     * fib4 = 6765,time = 0
     * Process finished with exit code 0
     * 参考：https://leetcode-cn.com/problems/fibonacci-number/solution/509-fei-bo-na-qi-shu-dong-tai-gui-hua-ru-nuyx/
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int fib4 = fib4(20);
        System.out.print("fib4 = " + fib4 + ",time = " + (System.currentTimeMillis() - start));
    }

    private static int fib4(int n) {
        if (n <= 1) {
            return n;
        }

        System.out.println("run fib4(" + n + ")");
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int sum = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = sum;
        }
        return dp[1];
    }
}
