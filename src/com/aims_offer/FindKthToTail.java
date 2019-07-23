package com.aims_offer;
//输入一个链表，输出该链表中倒数第k个结点。
public class FindKthToTail {
    public static ListNode FindKthToTail(ListNode head,int k) {
        ListNode node = new ListNode(0);
        if (head != null && k >= 0){
            int length = 1;
            node.next = head;
            while (node.next.next != null){
                node.next = node.next.next;
                length++;
            }
            length = length - k;
            if(length >= 0){
                node.next = head;
                while (length > 0){
                    node.next = node.next.next;
                    length--;
                }
                return node.next;
            }

        }
        return null;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l4.next = l5;
        l3.next = l4;
        l2.next = l3;
        l1.next = l2;
        System.out.println(FindKthToTail(l1,6).val);
    }
}
class ListNode{
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}