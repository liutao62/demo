package com.liutao62.leetcode;

/**
 * @author liutao
 * @date Created in 2021/6/17 23:32
 * @description
 */
public class _92reverseBetween {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }

        ListNode cursor = null, next = null, last = null, newHead = head, tail = null;
        int count = 1;
        // 取出不需要转换的头部
        while (head != null) {
            if (count == left) {
                break;
            }
            count++;
            last = head;
            head = head.next;
        }
        if (head == null) {
            return newHead;
        }
        boolean end = false;
        // 转换后的尾部
        tail = head;
        while (head != null) {
            cursor = head.next;
            if (count++ == right) {
                end = true;
            }
            head.next = next;
            next = head;
            head = cursor;
            if (end) {
                break;
            }
        }
        if (last != null) {
            last.next = next;
        }
        tail.next = head;
        return last == null ? next : newHead;
    }

    public static void main(String[] args) {
        ListNode listNode = new _92reverseBetween().reverseBetween(new ListNode(3, 5), 1, 2);
        System.out.println(listNode);
    }
}
