package com.java.xknowledge.se.collection.queue;

import java.util.PriorityQueue;

/**
 * 优先级队列实践：按照元素大小排序的队列
 * 运行：
 * [-3, 0, 9, 6]//按照元素的大小，从小到大金星排序
 * -3//访问队列
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂的Java讲义》
 */
class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        //下面代码依次向pq中加入四个元素
        pq.offer(6);
        pq.offer(-3);
        pq.offer(9);
        pq.offer(0);
        //输出pq队列，并不是按元素的加入顺序排列，
        //而是按元素的大小顺序排列，输出[-3, 0, 9, 6]
        System.out.println(pq);
        //访问队列第一个元素，其实就是队列中最小的元素：-3
        System.out.println(pq.poll());
    }
}
