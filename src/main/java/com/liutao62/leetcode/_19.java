package com.liutao62.leetcode;

public class _19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode node = head;
        int len = 1;
        while (node.next != null) {
            node = node.next;
            len++;
        }
        if (n == len) return head.next;
        node = head;
        for (int i = 1; i < len - n; i++) {
            node = node.next;
        }
        node.next = node.next.next;
        return head;
    }
}

