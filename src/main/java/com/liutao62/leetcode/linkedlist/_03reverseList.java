package com.liutao62.leetcode.linkedlist;

import com.liutao62.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2020/9/1 0:00
 * @description
 */
public class _03reverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode cursor = head;
        while (cursor != null) {
            list.add(cursor);
            cursor = cursor.next;
        }
        if (list.size() == 1) {
            return head;
        }
        for (int i = list.size() - 1; i > 0; i--) {
            list.get(i).next = list.get(i - 1);
        }
        list.get(0).next = null;
        return list.get(list.size() - 1);
    }
}
