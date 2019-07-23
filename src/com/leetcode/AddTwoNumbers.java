package com.leetcode;

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
}
public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode listNode = new ListNode(0);
        listNode.next = l1;
        while (l1 != null && l2 != null){
            if (l1.val + l2.val >= 10) {
                if (l1.next != null) l1.next.val++;
                else if (l2.next != null) l2.next.val++;
                else l1.next = new ListNode(1);
            }
            l1.val = (l1.val + l2.val) % 10;
            if (l1.next == null && l2.next != null) {
                l1.next = l2.next;
                break;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return listNode.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(7);
    //    l1.next = new ListNode(4);
    //    l1.next.next = new ListNode(3);
        l2.next = new ListNode(3);
     //   l2.next.next = new ListNode(4);
        ListNode listNode = addTwoNumbers(l1, l2);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
