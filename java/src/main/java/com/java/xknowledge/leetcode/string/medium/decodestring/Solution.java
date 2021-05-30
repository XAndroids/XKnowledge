package com.java.xknowledge.leetcode.string.medium.decodestring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 394. 字符串解码
 * 链接：https://leetcode-cn.com/problems/decode-string/
 */
class Solution {
    //处理字符串的索引，默认从0开始处理
    static int charIndex = 0;

    //辅助栈法
    //题解：https://leetcode-cn.com/problems/decode-string/solution/zi-fu-chuan-jie-ma-by-leetcode-solution/
    public static String decodeString(String s) {
        //参数校验
        if (s == null) return null;

        //辅助栈，用于保存字符串解码拼接字符串
        Deque<String> helpStack = new LinkedList<>();
        //当没有完成字符串s的索引之前，循环处理
        while (charIndex < s.length()) {
            //取出要处理的字符
            char deChar = s.charAt(charIndex);

            if (Character.isDigit(deChar)) {
                //如果当前字符是数位，解析出一个数字（连续多位）并进栈。如12[ab]等场景
                helpStack.push(getDigits(s));
            } else if (Character.isLetter(deChar) || deChar == '[') {
                //如果当前字符为字母或者左括号，则直接进栈
                helpStack.push(String.valueOf(deChar));
                charIndex++;
            } else {
                //如果当前字符为右括号，则开始出栈，直到是左括号拼接一个子字符串
                List<String> subList = new ArrayList<>();
                while (!helpStack.peek().equals("[")) {
                    subList.add(helpStack.poll());
                }
                //将出栈子字符串进行逆序，因为入栈2[bc]出栈是cb，逆序后bc在重复拼接
                //到了左括号，进行移除
                helpStack.poll();

                //左括号前面一定是数字，根据数字重复拼接字符子串
                String subString = getRevertListString(subList);
                StringBuilder subStringBuffer = new StringBuilder();
                int repInt = Integer.parseInt(helpStack.poll());
                while (repInt > 0) {
                    subStringBuffer.append(subString);
                    repInt--;
                }

                //将重复拼接的子子串，入栈进行后续的拼接处理
                helpStack.push(subStringBuffer.toString());
                //处理完右括号后，索引+1
                charIndex++;
            }
        }

        //当所有的字符都处理完之后，在栈里存在解码完成的字符子串
        return getRevertListString(helpStack);
    }

    //获取当前索引开始的字符，如果是多位数字获取一个独立的证书
    public static String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(charIndex))) {
            //每个数字字符索引+1，虽然只是一个数字
            ret.append(s.charAt(charIndex++));
        }
        return ret.toString();
    }

    //逆序从集合尾部到头部，进行字符串的拼接
    private static String getRevertListString(Collection<String> helpList) {
        StringBuilder listString = new StringBuilder();
        String[] helpArray = helpList.toArray(new String[0]);
        for (int i = helpArray.length - 1; i >= 0; i--) {
            listString.append(helpArray[i]);
        }
        return listString.toString();
    }

    public static void main(String[] args) {
//        String result = decodeString("2[a2[bc]]");
//        String result = decodeString("13[a]2[bc]");
        String result = decodeString("3[a2[c]]");
        System.out.println(result);
    }
}
