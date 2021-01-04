package com.java.xknowledge.arithmetic.stack.array;

/**
 * 栈的数组实现测试
 */
class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack(20);
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Now the top_num is:" + stack.peek());

        while (!stack.isEmpty()) {
            try {
                System.out.println(stack.pop());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
