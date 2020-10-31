package com.java.xknowledge.arithmetic.sort;


/**
 * 堆排序
 * 参考：《享学1：14、排序算法的实现总结及性能对比，应用场景》
 */
class HeapSort {
    //声明全局变量，用于记录数组array的长度
    private static int len;

    public static int[] sort(int[] array) {
        len = array.length;
        if (len < 1) {
            return array;
        }

        //构建一个最大堆
        buildMaxHeap(array);

        //循环将堆首位（最大值）与末位交换，然后重新调整最大堆
        while (len > 0) {
            swap(array, 0, len - 1);
            len--;//已经计算出最值了，并移动到len-1的位置，故减少索引，在重新调整最大堆的时候忽略
            adjustHeap(array, 0);
        }

        return array;
    }

    private static void buildMaxHeap(int[] array) {
        //从最后一个非叶节点开始向上，遍历所有父节点，构造最大堆
        for (int i = (len / 2 - 1); i >= 0; i--) {
            adjustHeap(array, i);//调整成为最大堆，最后非叶子节点开始
        }
    }

    /**
     * 调整最大堆
     *
     * @param array 调整的数组array
     * @param i     数组 最后非叶子节点索引
     */
    private static void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        int left = 2 * i + 1;//左节点索引
        int right = 2 * (i + 1);//右节点索引

        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if (left < len && array[left] > array[maxIndex]) {//left<len 忽略已经查找并交换的最大值
            maxIndex = left;
        }
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if (right < len && array[right] > array[maxIndex]) {
            maxIndex = right;
        }

        //如果父节点不是最大值，将父节点与最大值交换，并且递归调整与父节点交换位置
        if (maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(array, maxIndex);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
