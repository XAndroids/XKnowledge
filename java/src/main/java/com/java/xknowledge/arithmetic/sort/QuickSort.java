package com.java.xknowledge.arithmetic.sort;

/**
 * 快速排序
 * 参考：《享学1：14、排序算法的实现总结及性能对比，应用场景》
 */
class QuickSort {
    public static int[] sort(int[] array, int start, int end) {
        if (start < 0 || end >= array.length || start > end) {
            return null;
        }

        //数组分割成两部分，从哪儿分区指示器
        int zooeIndex = partition(array, start, end);
        //如果分区指示器在中间，则在指示器前后进行再次快速排序
        if (zooeIndex > start) {
            sort(array, start, zooeIndex - 1);
        }
        if (zooeIndex < end) {
            sort(array, zooeIndex + 1, end);
        }
        return array;
    }

    /**
     * 按照快速排序算法
     */
    private static int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));//基准数
        int zoneIndex = start - 1;//分区指示器
        //将基准数和尾元素交换位置
        swap(array, pivot, end);
        for (int i = start; i <= end; i++) {
            if (array[i] <= array[end]) {//当前元素小于等于基准数组
                zoneIndex++;//分隔指示器累加
                if (i > zoneIndex) {//当前元素在分隔指示器右边时，交换当前元素和分隔指示器元素
                    swap(array, i, zoneIndex);
                }
            }
        }

        return zoneIndex;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
