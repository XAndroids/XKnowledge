package com.java.xknowledge.leetcode.array.easy.finddisappearednumbers;

import java.util.ArrayList;
import java.util.List;


/**
 * 448. 找到所有数组中消失的数字
 * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 */
class Solution {
    /**
     * 辅助集合法：辅助集合记录数组nums，遍历1~nums.lenght，判断集合中不存在的返回
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //将nums数组转换成集合
        List<Integer> numsList = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            numsList.add(nums[i]);
        }

        List<Integer> resutList = new ArrayList<>();
        //遍历所有应该展示的数字
        for (int i = 1; i <= nums.length; i++) {
            //如果没有展示过，则记录结果
            if (!numsList.contains(i)) {
                resutList.add(i);
            }
        }

        return resutList;
    }

    /**
     * 原地修改法
     * 使用%运算，即统计了是否存在，虽然修改了原有值，但也保留了原有数据意义
     * 再次遍历，< n的都是没有统计的，即没有存在的；
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;//计算元素映射的索引位置
            nums[x] += n;//将索引位置的数据+n，当再次计算时%n，不影响原有数据的统计
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {//如果nums[i]<=n即，没有当前索引的数据，则添加返回
                ret.add(i + 1);
            }
        }
        return ret;
    }
}
