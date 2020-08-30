package com.liutao62.leetcode.linkedlist;

import com.liutao62.leetcode.ListNode;

/**
 * @author liutao
 * @date Created in 2020/8/30 22:08
 * @description
 */
public class _01deleteNode {
    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
    }
}
