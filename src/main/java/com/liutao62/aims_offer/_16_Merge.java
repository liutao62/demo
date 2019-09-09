package com.liutao62.aims_offer;

/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class _16_Merge {
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode node = new ListNode(0);
        ListNode cur = new ListNode(0);
        cur.next = node;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next.next = list1;
                list1 = list1.next;
            } else {
                cur.next.next = list2;
                list2 = list2.next;
            }
            cur.next = cur.next.next;
        }
        if (list1 == null) cur.next.next = list2;
        if (list2 == null) cur.next.next = list1;
        return node.next;
    }
}
