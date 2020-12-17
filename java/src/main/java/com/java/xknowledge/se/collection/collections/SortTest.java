package com.java.xknowledge.se.collection.collections;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Collections实践：排序相关，反转、自然排序，随机排序
 * [2, -5, 3, 0]
 * [0, 3, -5, 2]
 * [-5, 0, 2, 3]
 * [0, 2, 3, -5]
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂Java讲义》
 */
class SortTest {
    public static void main(String[] args) {
        ArrayList nums = new ArrayList();
        nums.add(2);
        nums.add(-5);
        nums.add(3);
        nums.add(0);
        //输出:[2, -5, 3, 0]
        System.out.println(nums);

        //将List集合元素的次序反转
        Collections.reverse(nums);
        //输出:[0, 3, -5, 2]
        System.out.println(nums);

        //将List集合元素的按自然顺序排序
        Collections.sort(nums);
        //输出:[-5, 0, 2, 3]
        System.out.println(nums);

        //将List集合元素的按随机顺序排序
        Collections.shuffle(nums);
        //每次输出的次序不固定
        System.out.println(nums);
    }
}
