package com.java.xknowledge.arithmetic.sort;

import java.util.ArrayList;

/**
 * 基数排序
 * 参考：《享学1：14、排序算法的实现总结及性能对比，应用场景》
 */
class RadixSort {
    public static int[] sort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }

        //找出最大数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }

        //找出最大数位数
        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }

        //构建桶
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<Integer>());
        }


        int mod = 10, div = 1;
        //从个位开始，第i轮遍历根据数据第i位大小num，找到对应的桶bucketList.get(num)放入数据
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int j = 0; j < array.length; j++) {//遍历数据
                int num = (array[j] % mod) / div;//计算"位数"
                bucketList.get(num).add(array[j]);//然后放入对应的桶总
            }

            //从低到高遍历桶，从桶中取出数据放入原数据array中，并清除桶，进行下一轮排序
            int index = 0;
            for (int m = 0; m < bucketList.size(); m++) {
                for (int n = 0; n < bucketList.get(m).size(); n++) {
                    array[index++] = bucketList.get(m).get(n);
                    bucketList.get(m).clear();
                }
            }
        }

        return array;
    }
}
