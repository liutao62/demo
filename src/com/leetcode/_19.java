package com.leetcode;

public class _19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        int len = 0;
        while (node.next != null) {
            node = node.next;
            len++;
        }
        len++;
        node = new ListNode(0);
        node.next = head;
        for (int i = 0; i < len - n; i++) {
            node = node.next;
        }
        node.next = node.next.next;
        return head;
    }
}

