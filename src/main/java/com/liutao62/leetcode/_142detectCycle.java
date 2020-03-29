package com.liutao62.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _142detectCycle {
    public static ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head.next)) {
                return head.next;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }
}
