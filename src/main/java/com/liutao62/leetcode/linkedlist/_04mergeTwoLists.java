package com.liutao62.leetcode.linkedlist;

import com.liutao62.leetcode.ListNode;

/**
 * @author liutao
 * @date Created in 2020/9/1 0:07
 * @description
 */
public class _04mergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode(0), cursor = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                cursor.next = l2;
                l2 = l2.next;
            } else {
                cursor.next = l1;
                l1 = l1.next;
            }
            cursor = cursor.next;
        }
        if (l1 != null) {
            cursor.next = l1;
        }
        if (l2 != null) {
            cursor.next = l2;
        }
        return head.next;
    }
}
