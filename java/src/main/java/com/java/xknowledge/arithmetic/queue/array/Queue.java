package com.java.xknowledge.arithmetic.queue.array;

/**
 * 队列数组实现
 * 参考：https://blog.csdn.net/qq_21808961/article/details/76239094
 */
class Queue {
    private int[] queue;//队列数组
    private int head = 0;//头下标
    private int tail = 0;//尾下标
    private int count = 0;//元素个数

    public Queue() {
        queue = new int[10];
        this.head = 0;//头下标为零
        this.tail = 0;
        this.count = 0;
    }

    public Queue(int size) {
        queue = new int[size];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    //入队
    public boolean inQueue(int t) {
        if (count == queue.length)
            return false;
        queue[tail++ % (queue.length)] = t;//如果不为空就放入下一个
        count++;
        return true;
    }

    //出队
    public int outQueue() {
        if (count == 0)//如果是空的那就不能再出栈了
            return -1;
        count--;
        return queue[head++ % (queue.length)];
    }

    //查队头
    public int showHead() {
        if (count == 0) return -1;
        return queue[head];
    }

    //判满
    public boolean isFull() {
        return count == queue.length;
    }

    //判空
    public boolean isEmpty() {
        return count == 0;
    }
}
