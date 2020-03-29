package com.liutao62.leetcode;

import java.util.LinkedList;

public class _206reverseList {
    public static ListNode reverseList(ListNode head) {
        LinkedList<ListNode> queue = new LinkedList<>();
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
