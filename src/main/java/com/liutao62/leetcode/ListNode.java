package com.liutao62.leetcode;

import lombok.experimental.Accessors;

@Accessors(chain = true)
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public String toString() {
        return "node " + this.val;
    }
}