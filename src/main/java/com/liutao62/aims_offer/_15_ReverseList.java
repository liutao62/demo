package com.liutao62.aims_offer;

//输入一个链表，反转链表后，输出新链表的表头。
public class _15_ReverseList {
    public ListNode ReverseList(ListNode head) {
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
