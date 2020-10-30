package com.java.xknowledge.arithmetic.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 桶排序
 * 参考：《享学1：14、排序算法的实现总结及性能对比，应用场景》
 */
class BucketSort {
    public static List<Integer> sort(List<Integer> array, int bucketSize) {
        if (array == null || array.size() < 2) {
            return array;
        }

        //找到最大值和最小值
        int max = array.get(0), min = array.get(0);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > max) {
                max = array.get(i);
            }
            if (array.get(i) < min) {
                min = array.get(i);
            }
        }

        //根据最大和最小值的差值，和桶的数量，计算桶的数量
        int bucketCount = (max - min) / bucketSize + 1;

        //构建桶
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }

        //将原数组计算在桶的位置array.get(i) - min，然后将数据分配到这个桶中
        for (int i = 0; i < array.size(); i++) {
            bucketArr.get((array.get(i) - min) / bucketSize).add(array.get(i));
        }

        //对桶中的数据进行排序
        ArrayList<Integer> resultArr = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSize == 1) {//如果桶的大小为1，桶内没必要排序，直接添加到结果数据中
                resultArr.addAll(bucketArr.get(i));
            } else {
                if (bucketCount == 1) {
                    bucketSize--;
                }
                //对桶中的数据再次进行桶排序操作
                resultArr.addAll(sort(bucketArr.get(i), bucketSize));
            }
        }

        return resultArr;
    }
}
