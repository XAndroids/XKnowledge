package com.java.xknowledge.leetcode.list.easy.ispalindrome;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 234. 回文链表
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list/
 */
class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        /**
         * 前后指针法:由于链表前后遍历操作不方便，故先转换成集合，然后通过前后指针判断是否是回文
         */
        public boolean isPalindrome(ListNode head) {
            List<Integer> integerList = new ArrayList<>();//将链表转换成集合，便于前后指针判断
            while (head != null) {
                integerList.add(head.val);
                head = head.next;
            }

            int start = 0;
            int end = integerList.size() - 1;
            while (start < end) {//从结合的前后比较相等判断是否是回文
                if (!integerList.get(start).equals(integerList.get(end))) {
                    return false;//只要有一个不满足就返回false
                }
                start++;//从外向内一次判断
                end--;
            }

            return true;//如果都满足，则返回true
        }

        /**
         * 栈方法：通过入栈和出栈字符反转顺序，进行前后节点的对比
         */
        public boolean isPalindrome2(ListNode head) {
            //通过入栈和出栈，反转链表
            Deque<Integer> stackInteger = new LinkedList<>();
            ListNode tmp = head;
            while (tmp != null) {//遍历将链表节点入栈
                stackInteger.push(tmp.val);
                tmp = tmp.next;
            }

            //通过正常遍历和出栈顺序判断是否是回文
            ListNode tmp2 = head;
            while (tmp2 != null) {
                if (tmp2.val != stackInteger.pop()) {
                    return false;
                }
                tmp2 = tmp2.next;
            }

            return true;
        }
    }
}
