package com.aims_offer;

import java.util.ArrayList;

/*
时间限制：1秒 空间限制：32768K 热度指数：680153
本题知识点： 链表

题目描述
    输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
*/
/*class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/

public class PrintListFromTailToHead {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        listNode4.next = listNode5;
        listNode3.next = listNode4;
        listNode2.next = listNode3;
        listNode1.next = listNode2;
        listNode.next = listNode1;
        ArrayList<Integer> list = printListFromTailToHead(listNode);

    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode node = new ListNode(0);
        node.next = listNode;
        while (node.next != null) {
            while (node.next.next != null) {
                if(node.next.next.next != null){
                    node.next = node.next.next;
                }else {
                    list.add(node.next.next.val);
                    node.next.next = null;
                }
            }
            node.next = listNode;
            if (node.next.next == null){
                list.add(node.next.val);
                break;
            }

        }

        return list;
    }

}
