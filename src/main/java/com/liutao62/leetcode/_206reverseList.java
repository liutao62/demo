package com.liutao62.leetcode;

public class _206reverseList {
    public static ListNode reverseList(ListNode head) {
        ListNode tem = null, cursor;
        while (head != null) {
            cursor = head.next;
            head.next = tem;
            tem = head;
            head = cursor;
        }
        return tem;
    }
}
