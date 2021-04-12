package com.java.xknowledge.leetcode.list.medium.addtwonumbers;

/**
 * 2. 两数相加
 * 链接：https://leetcode-cn.com/problems/add-two-numbers/
 * 题解：https://leetcode-cn.com/problems/add-two-numbers/solution/hua-jie-suan-fa-2-liang-shu-xiang-jia-by-guanpengc/
 */
class Solution {
    public static class ListNode {
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
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();//返回新链表结果，定义辅助头节点，返回head.next
        ListNode cur = head;//定义当前节点，用于串联链表

        int carry = 0;//进位标识，用户每次计算和保留进位
        while (l1 != null || l2 != null) {//当两个链表还没有到结尾时
            int num1 = l1 != null ? l1.val : 0;//获取两个链表当前节点的值
            int num2 = l2 != null ? l2.val : 0;

            int sum = num1 + num2 + carry;//求和运算
            cur.next = new ListNode(sum % 10);//构造新相加节点
            carry = sum / 10;//计算进位

            if (l1 != null) {//后移相加链表和结果链表
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            cur = cur.next;
        }

        //遍历完成后，如果还有进位，则构造新节点
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }

        return head.next;
    }
}
