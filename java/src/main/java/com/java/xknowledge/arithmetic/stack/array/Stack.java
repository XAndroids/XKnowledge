package com.java.xknowledge.arithmetic.stack.array;

/**
 * 栈数组结构实现
 * 参考：https://blog.csdn.net/bitcarmanlee/article/details/51537138
 */
class Stack {
    //存数据的数组
    int[] data;

    //栈的最大长度
    private int size;
    //栈顶的位置
    private int top;

    public Stack(int size) {
        this.size = size;
        data = new int[size];
        top = -1;
    }

    public int getSize() {
        return size;
    }

    public int getTop() {
        return top;
    }

    /**
     * 判断是否为空栈
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 判断是否为满栈
     */
    public boolean isFull() {
        return (top + 1) == size;
    }

    /**
     * 压栈操作
     */
    public boolean push(int data) {
        if (isFull()) {
            System.out.println("the stack is full!");
            return false;
        } else {
            top++;
            this.data[top] = data;
            return true;
        }
    }


    /**
     * 弹栈操作
     */
    public int pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("the stack is empty!");
        } else {
            return this.data[top--];
        }
    }

    /**
     * 获取栈顶的元素,但不弹栈
     */
    public int peek() {
        return this.data[getTop()];
    }
}
