package com.liutao62.leetcode.linkedlist;

import com.liutao62.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2020/8/30 22:15
 * @description
 * @see com.liutao62.leetcode._19
 */
public class _02removeNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        list.add(cur);
        int len = 0;
        while (cur != null) {
            cur = cur.next;
            list.add(cur);
            len++;
        }
        int count = len - n;
        if (count == 0) {
            if (len == 1) {
                return null;
            }
            return list.get(1);
        } else if (count == len) {
            list.get(count - 1).next = null;
            return list.get(0);
        }
        list.get(count - 1).next = list.get(count + 1);
        return list.get(0);
    }
}

