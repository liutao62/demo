package com.aims_offer;
//输入一个链表，反转链表后，输出新链表的表头。
public class ReverseList {
    public static ListNode ReverseList(ListNode head) {
        if (head != null){
            ListNode cur = null,next = null;
            while (head.next != null){
                cur = head.next;
                head.next = next;
                next = head;
                head = cur;
            }
            head.next = next;
            return head;
        }
        return null;
    }
}
