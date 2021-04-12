package com.java.xknowledge.leetcode.array.medium.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 题目：https://leetcode-cn.com/problems/3sum/
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resutList = new ArrayList<>();
        if (nums == null || nums.length < 3) {//剪枝校验不合法参数，返回空列表
            return resutList;
        }

        Arrays.sort(nums);//排序数组，后面求和之后可以顺序遍历

        for (int i = 0; i < nums.length; i++) {//从num[i]为标准，查找sum=0组合
            //当num[i]>0，left+right一定不会=0
            if (nums[i] > 0) continue;
            //下一个数组重复，则不用计算直接遍历下一个
            if (i > 0 && nums[i - 1] == nums[i]) continue;

            //计算当前num[i]的left和right索引
            int left = i + 1;
            int right = nums.length - 1;

            //如果left和right不相交，则进行检查
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];//计算sum结果

                if (sum == 0) {
                    //如果sum==0，则添加结果集
                    resutList.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    //遍历left和right左右相同数字，则同样满足sum=0，直接忽略不重复计算
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    //进一步计算剩余可能的组合
                    left++;
                    right--;
                } else if (sum > 0) {
                    //如果sum大了，right左移动
                    right--;
                } else if (sum < 0) {
                    //如果sum小了，left右移动
                    left++;
                }
            }
        }

        //返回结果集
        return resutList;
    }
}
