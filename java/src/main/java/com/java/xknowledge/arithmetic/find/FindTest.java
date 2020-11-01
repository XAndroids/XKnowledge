package com.java.xknowledge.arithmetic.find;

/**
 * 查找测试类
 */
class FindTest {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};// 源数据
        int key = 3;
        int ret = BinarySearch.search(arr, key);
        System.out.println(ret);
    }
}
