package com.liutao62.leetcode;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */
public class _237deleteNode {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
