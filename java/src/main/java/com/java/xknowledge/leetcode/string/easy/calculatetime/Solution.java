package com.java.xknowledge.leetcode.string.easy.calculatetime;

import java.util.HashMap;

/**
 * 1165. 单行键盘
 * 链接：https://leetcode-cn.com/problems/single-row-keyboard/
 */
class Solution {

    public int calculateTime(String keyboard, String word) {
        //哈希存储字符和字符索引
        HashMap<Character, Integer> charactersMap = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            charactersMap.put(keyboard.charAt(i), i);
        }

        //测试总时间和上一次点击时间
        int timeTotal = 0;
        int preTime = 0;
        //遍历word计算没两次按键之间的距离即事件
        for (int j = 0; j < word.length(); j++) {
            timeTotal += Math.abs(charactersMap.get(word.charAt(j)) - preTime);
            preTime = charactersMap.get(word.charAt(j));
        }

        return timeTotal;
    }
}
