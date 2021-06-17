package com.liutao62.leetcode;

import lombok.experimental.Accessors;

@Accessors(chain = true)
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int... x) {
        val = x[0];
        ListNode temp = this;
        for (int i = 1; i < x.length; i++) {
            temp.next = new ListNode(x[i]);
            temp = temp.next;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.val);
        ListNode temp = this.next;
        while (temp != null) {
            sb.append("->" + temp.val);
            temp = temp.next;
        }
        return sb.toString();
    }
}