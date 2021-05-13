package com.java.xknowledge.leetcode.other.findmindifference;

import java.util.Arrays;
import java.util.List;

/**
 * 539. 最小时间差
 * 链接：https://leetcode-cn.com/problems/minimum-time-difference/
 */
class Solution {
    public int findMinDifference(List<String> timePoints) {
        //将数据转化成分钟
        int[] minuTimePoints = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            minuTimePoints[i] = minuTimePoints(timePoints.get(i));
        }
        //计算最小时间差前先排序，就没必要计算两两之间的时间差了
        Arrays.sort(minuTimePoints);

        int diffminu = Integer.MAX_VALUE;
        //存在收尾时间差大于12小时情况，计算增加1天计算是否最小
        for (int j = 1; j < minuTimePoints.length; j++) {
            diffminu = Math.min(diffminu, minuTimePoints[j] - minuTimePoints[j - 1]);
        }

        //头部数据+1天，计算收尾时间差是不是更小
        diffminu = Math.min(diffminu, (minuTimePoints[0] + 24 * 60 - minuTimePoints[
                minuTimePoints.length - 1]));

        return diffminu;
    }

    //将时间转换成分钟
    public int minuTimePoints(String timePoint) {
        return (int) timePoint.charAt(0) * 10 * 60 + (int) timePoint.charAt(1) * 60 + timePoint
                .charAt(3) * 10 + timePoint.charAt(4);
    }
}
