package com.java.xknowledge.arithmetic.stack.linked;

/**
 * 栈链表实现
 * 参考：https://www.codenong.com/cs105138486/
 */
class Stack {

    Node header;//栈顶元素
    int elementCount;//栈内元素个数
    int size;//栈的大小

    /**
     * 构造函数，构造一个空的栈
     */
    public Stack() {
        header = null;
        elementCount = 0;
        size = 0;
    }

    /**
     * 通过构造器自定义栈的大小
     */
    public Stack(int size) {
        header = null;
        elementCount = 0;
        this.size = size;
    }

    public void setHeader(Node header) {
        this.header = header;
    }

    public boolean isFull() {
        return elementCount == size;
    }

    public boolean isEmpty() {
        return elementCount == 0;
    }

    /**
     * 入栈
     */
    public void push(Object value) {
        if (this.isFull()) {
            throw new RuntimeException("Stack is Full");
        }

        //注意这里面试将原来的header作为参数传入，然后以新new出来的Node作为header
        header = new Node(value, header);
        elementCount++;
    }

    /**
     * 出栈
     */
    public Object pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        Object object = header.getElement();
        header = header.next;
        elementCount--;
        return object;
    }

    /**
     * 返回栈顶元素
     */
    public Object peek() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        return header.getElement();
    }

    class Node {
        Object element;
        Node next;

        public Node(Object element) {
            this(element, null);
        }

        /**
         * 创建一个新的节点
         * 让他的next指向，参数中的节点
         */
        public Node(Object element, Node n) {
            this.element = element;
            next = n;
        }

        public Object getElement() {
            return element;
        }
    }
}
