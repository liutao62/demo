package com.liutao62.leetcode;

public class _148sortList {
    public static ListNode sortList(ListNode head) {
        if (head == null) return null;
        ListNode cur1, cur2 = head.next;
        int tem;
        while (cur2 != null) {
            cur1 = head;
            while (cur1 != cur2) {
                if (cur1.val > cur2.val) {
                    tem = cur1.val;
                    cur1.val = cur2.val;
                    cur2.val = tem;
                }
                cur1 = cur1.next;
            }
            cur2 = cur2.next;
        }
        return head;
    }
}
