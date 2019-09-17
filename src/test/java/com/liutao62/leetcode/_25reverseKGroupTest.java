package com.liutao62.leetcode;

import org.junit.Before;
import org.junit.Test;

public class _25reverseKGroupTest {
    private _25reverseKGroup test = new _25reverseKGroup();

    ListNode node = new ListNode(1);

    @Before
    public void before() {
        ListNode temp = node;
        int count = 2;
        while (count < 10) {
            temp.next = new ListNode(count);
            temp = temp.next;
            count++;
        }
    }

    @Test
    public void reverseKGroup() {

        test.reverseKGroup(node, 3);
    }
}