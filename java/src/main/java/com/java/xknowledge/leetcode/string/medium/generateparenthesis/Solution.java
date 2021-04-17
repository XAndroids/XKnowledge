package com.java.xknowledge.leetcode.string.medium.generateparenthesis;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 题目：https://leetcode-cn.com/problems/generate-parentheses/
 * 题解：https://leetcode-cn.com/problems/generate-parentheses/solution/gua-hao-sheng-cheng-by-leetcode-solution/
 */
class Solution {
    public List<String> generateParenthesis(int n) {
        //检查参数合法性
        if (n <= 0) {
            return null;
        }

        //递归方法生成所有可能的括号集合
        List<String> result = new ArrayList<>();
        dfs(0, new char[2 * n], result);//直接传递new Char[2*n]进行递归组合，每个结果都创建String
        return result;
    }

    /**
     * 递归穷举所有可能括号组合
     */
    private void dfs(int depth, char[] current, List<String> result) {
        if (depth == current.length) {//如果递归叶子节点，说明组合完毕添加到结果集合中
            if (isLegal(current)) {//直接在添加结果集合之前校验合法性
                result.add(new String(current));
            }
            return;
        }

        current[depth] = '(';
        dfs(depth + 1, current, result);
        current[depth] = ')';//执行完一种情况遍历之后，回溯进行另一种情况递归，保证全程使用一个char[]
        dfs(depth + 1, current, result);
    }

    /**
     * 括号是否合法，不使用辅助栈结构，直接判断
     */
    private boolean isLegal(char[] charArray) {
        int balance = 0;
        for (char c : charArray) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }

            if (balance < 0) {
                return false;
            }
        }

        return balance == 0;
    }

    public static void main(String[] args) {

    }
}
