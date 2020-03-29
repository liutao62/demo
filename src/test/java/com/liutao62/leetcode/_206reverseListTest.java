package com.liutao62.leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class _206reverseListTest {

    ListNode head = new ListNode(1);
    ListNode tem = head;

    @Before
    public void before() {
        int i = 1;
        while (i < 5) {
            tem.next = new ListNode(i + 1);
            tem = tem.next;
            i++;
        }

    }

    @Test
    public void reverseList() {
        _206reverseList.reverseList(head);
    }
}