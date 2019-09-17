package com.liutao62.leetcode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public String toString() {
        return "node " + this.val;
    }
}

public class _02 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(1);
        ListNode temp = node;
        int p1, p2, sum;
        int flag = 0;
        while (l1 != null || l2 != null) {
            p1 = l1 == null ? 0 : l1.val;
            p2 = l2 == null ? 0 : l2.val;
            sum = p1 + p2 + flag;
            flag = sum >= 10 ? 1 : 0;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (flag == 1) temp.next = new ListNode(1);
        return node.next;
    }
}
