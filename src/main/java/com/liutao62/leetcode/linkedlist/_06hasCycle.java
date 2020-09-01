package com.liutao62.leetcode.linkedlist;

import com.liutao62.leetcode.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liutao
 * @date Created in 2020/9/1 21:50
 * @description
 */
public class _06hasCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }
}
