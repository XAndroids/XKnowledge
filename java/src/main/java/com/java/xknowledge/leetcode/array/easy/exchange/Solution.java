package com.java.xknowledge.leetcode.array.easy.exchange;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * 链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 */
class Solution {
    /**
     * 辅助数组法
     */
    public int[] exchange(int[] nums) {
        int jiIndex = 0;
        int ouIndex = nums.length - 1;//使用双索引表示分类存放位置，从前后两端进行存储避免数组移动
        int[] result = new int[nums.length];//使用辅助数组保存分类好的数据

        for (int i = 0; i <= nums.length - 1; i++) {
            if (nums[i] % 2 != 0) {
                result[jiIndex++] = nums[i];
            } else {
                result[ouIndex--] = nums[i];
            }
        }

        return result;
    }

    /**
     * 首尾双指针法
     */
    public int[] exchange2(int[] nums) {
        int jiIndex = 0;
        int ouIndex = nums.length - 1;//双索引表示分类存放位置

        while (jiIndex < ouIndex) {//索引交叉前继续运行
            if (nums[jiIndex] % 2 != 0) {//如果左边为奇数右移
                jiIndex++;
                continue;
            }
            if (nums[ouIndex] % 2 == 0) {//如果右边是偶数左移，注意：如果左边是奇数右边也是奇数，则右边不动！
                ouIndex--;
                continue;
            }
            swap(nums, jiIndex++, ouIndex--);//如果左边是奇数且右边是偶数，交换
        }

        return nums;
    }

    private void swap(int[] nums, int jiIndex, int ouIndex) {
        int temp = nums[jiIndex];
        nums[jiIndex] = nums[ouIndex];
        nums[ouIndex] = temp;
    }

    public static void main(String[] args) {

    }
}
