package com.java.xknowledge.arithmetic.find.binary;

/**
 * 二分查找
 * 参考：《享学1：17 二分查找算法》
 */
class BinarySearch {

    /**
     * 二分查找array中是否存在a值，如果有返回a在数组中的索引，如果没有返回-1
     *
     * @param arrary 要是有序的数组
     * @param a      要查找的数据
     * @return 查找数据在数组中的索引
     */
    public static int search(int[] array, int a) {
        int start = 0;//当前数组的开始和结束为止
        int end = array.length - 1;

        int mid;
        while (start <= end) {//如果start和end没有交叉，循环使用二分进行查找
            mid = (start + end);

            if (array[mid] < a) {
                start = mid + 1;
            } else if (array[mid] > a) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
