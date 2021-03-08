package com.java.xknowledge.leetcode.reverseList;

import org.junit.jupiter.api.Test;

class SolutionTests {
    @Test
    void testReverseList() {
        Solution.ListNode listNode3 = new Solution.ListNode(3);
        Solution.ListNode listNode2 = new Solution.ListNode(2);
        listNode2.next = listNode3;
        Solution.ListNode listNode1 = new Solution.ListNode(1);
        listNode1.next = listNode2;

        Solution.reverseList(listNode1);
    }
}
