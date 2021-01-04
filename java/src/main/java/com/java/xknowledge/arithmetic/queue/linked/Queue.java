package com.java.xknowledge.arithmetic.queue.linked;

/**
 * 队列链表实现
 * 参考：https://blog.csdn.net/ljxbbss/article/details/78141632
 */
class Queue {
    Node front;//队头指针，指向队头节点
    Node rail;//队尾指针，指向队尾节点

    int size = 0;//记录队列长度

    //构造函数
    public Queue() {
        front = rail = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入队
     */
    public boolean inQueue(Object ele) {
        if (size == 0) {
            front = new Node(ele, null);
            rail = front;
            size++;
            return true;
        }

        Node s = new Node(ele, null);
        //这块有个主意的地方，一旦rail设置了next属性，因为front节点与rail节点指向了同一个node节点，持有同一个结
        //点的一个引用，因此front节点next属性也被填充
        rail.setNext(s);
        rail = s;

        size++;
        return true;
    }


    /**
     * 出队列
     */
    public Object outQueue() {
        if (isEmpty()) {
            System.out.println("当前队列为空");
            return -1;
        }
        Object element = front.element;

        front = front.next;
        size--;

        return element;
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

        public void setNext(Node next) {
            this.next = next;
        }

        public Object getElement() {
            return element;
        }
    }
}
