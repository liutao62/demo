package com.liutao62.aims_offer;

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

public class _03_PrintListFromTailToHead {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode node = new ListNode(0);
        node.next = listNode;
        while (node.next != null) {
            while (node.next.next != null) {
                if (node.next.next.next != null) {
                    node.next = node.next.next;
                } else {
                    list.add(node.next.next.val);
                    node.next.next = null;
                }
            }
            node.next = listNode;
            if (node.next.next == null) {
                list.add(node.next.val);
                break;
            }

        }
        return list;
    }

}
