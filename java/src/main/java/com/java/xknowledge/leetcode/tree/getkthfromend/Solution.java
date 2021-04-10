package com.java.xknowledge.leetcode.tree.getkthfromend;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 */
class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 统计长度法
     */
    public ListNode getKthFromEnd1(ListNode head, int k) {
        //第一遍历统计链表的长度
        int listLength = 0;
        ListNode temp = head;
        while (temp != null) {
            listLength++;
            temp = temp.next;
        }

        //第二次遍历，根据倒数距离k，计算正序遍历距离startIndex
        int startIndex = listLength - k;
        ListNode temp2 = head;
        while (temp2 != null) {
            if (startIndex == 0) {//如果遍历到返回处，返回temp2
                return temp2;
            }

            startIndex--;
            temp2 = temp2.next;
        }
        return null;
        String s = new String();
        s.replaceAll()
    }

    /**
     * 双指针法：font指针先移动k个位置，然后end指针距离font指针k个位置开始移动。当start指针到末尾时，end指针
     * 正好距离末尾k个位置
     */
    public ListNode getKthFromEnd2(ListNode head, int k) {
        ListNode font = head;
        while (k > 0) {//先让font指针移动k个位置
            font = font.next;
            k--;
        }

        ListNode end = head;//end指针距离start k个位置
        while (font != null) {//当font到末尾时，start距离末尾k个位置，返回
            font = font.next;
            end = end.next;
        }

        return end;
    }
}
