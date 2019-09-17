package com.liutao62.leetcode;

/**
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 */
public class _25reverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;

        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        if (len < k) return head;

        // last : 反转前的头节点 cur ：遍历游标
        ListNode last = null, cur = head;
        // pre ：反转后的头节点 temp:保存cur.next
        ListNode pre = null,newHead = null;
        temp = head;
        int num = k;
        while (cur != null && len >= k) {
            ListNode begin = cur;
            cur = temp;
            while (cur != null && num > 0) {
                temp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
                num--;
            }

            if (last == null){
                newHead = pre;
            }else {
                // 将上次反转的尾节点指向这次反转的头节点
                last.next = pre;
            }
            last = begin;
            len -= k;
            num = k;
        }
        last.next = cur;
        return newHead;
    }

}
