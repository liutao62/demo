package com.liutao62.demo;

import com.liutao62.leetcode.ListNode;
import org.junit.Test;

public class CVTETest {

    private CVTE test= new CVTE();

    private ListNode head = new ListNode(0);

    @Test
    public void getMidNode() {
        ListNode temp = head;
        int count = 1;
        while (count < 12) {
            temp.next = new ListNode(count);
            temp = temp.next;
            count++;
        }
        System.out.println(test.getMidNode(head));
    }

    @Test
    public void getRandomArray() {
    }
}