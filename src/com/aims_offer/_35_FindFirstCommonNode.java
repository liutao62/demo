package com.aims_offer;

/**
 * 输入两个链表，找出它们的第一个公共结点。
 */
public class _35_FindFirstCommonNode {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode temp1 = pHead1, temp2 = pHead2;
        while (temp1 != null) {
            temp2 = pHead2;
            while (temp2 != null) {
                if (temp1 == temp2) return temp1;
                else temp2 = temp2.next;
            }
            temp1 = temp1.next;
        }
        return null;
    }
}
