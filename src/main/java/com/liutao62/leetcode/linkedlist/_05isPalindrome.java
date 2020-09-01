package com.liutao62.leetcode.linkedlist;

import com.liutao62.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2020/9/1 21:38
 * @description
 */
public class _05isPalindrome {
    /**
     * @param head
     * @return
     * @description time:O(3/2N) space:O(N)
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int size = list.size();
        int halfSize = size >> 1;
        for (int i = 0; i < halfSize; i++) {
            if (list.get(i).val != list.get(size - i - 1).val) {
                return false;
            }
        }
        return true;
    }
}
