package com.liutao62.leetcode;

/**
 * @author liutao
 * @date Created in 2021/6/16 23:25
 * @description
 */
public class _876middleNode {
    public ListNode middleNode(ListNode head) {
        ListNode cur = head;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            if (next != null) {
                head = next.next;
                cur = cur.next;
            } else {
                break;
            }
        }
        return cur;
    }
}
