package com.liutao62.aims_offer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liutao
 * @date Created in 2020/12/15 15:02
 * @description 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 * 示例1
 * 输入
 * {1,2,3,3,4,4,5}
 * 返回值
 * {1,2,5}
 */
public class _56deleteDuplication {
    public ListNode deleteDuplication(ListNode pHead) {
        Set<Integer> all = new HashSet<>();
        Set<Integer> repeat = new HashSet<>();
        if (pHead == null) {
            return null;
        }
        ListNode cursor = pHead;
        while (cursor != null) {
            if (all.contains(cursor.val)) {
                repeat.add(cursor.val);
            }
            all.add(cursor.val);
            cursor = cursor.next;
        }
        cursor = pHead;
        ListNode newHead = new ListNode(-1), pre = newHead;
        newHead.next = pHead;
        while (cursor != null) {
            if (repeat.contains(cursor.val)) {
                pre.next = cursor.next;
            } else {
                pre = cursor;
            }
            cursor = cursor.next;
        }
        return newHead.next;
    }
}
