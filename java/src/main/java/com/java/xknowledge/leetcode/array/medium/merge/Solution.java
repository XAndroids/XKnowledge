package com.java.xknowledge.leetcode.array.medium.merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间
 * 题目：https://leetcode-cn.com/problems/merge-intervals/
 * 题解：https://leetcode-cn.com/problems/merge-intervals/solution/he-bing-qu-jian-by-leetcode-solution/
 */
class Solution {

    public int[][] merge(int[][] intervals) {
        //校验参数
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        //对数组集合,针对int[0]进行升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> mergeList = new ArrayList<>();//Merge合并的区间集合
        for (int i = 0; i < intervals.length; i++) {
            //如果mergeList为空，则立即添加到merge结果，或者当前mergeList区间和比较区间不重合
            if (mergeList.isEmpty() || mergeList.get(mergeList.size() - 1)[1] < intervals[i][0]) {
                //则将新区间添加到集合中
                mergeList.add(intervals[i]);
            } else {
                //如果重合，则更新当前mergeList区间为新的重合区间
                mergeList.get(mergeList.size() - 1)[1] = Math.max(mergeList.get(mergeList.size() -
                        1)[1], intervals[i][1]);
            }
        }

        //将mergeList转变成int[][]
        return mergeList.toArray(new int[mergeList.size()][]);
    }
}
