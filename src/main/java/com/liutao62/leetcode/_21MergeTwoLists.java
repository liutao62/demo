package com.liutao62.leetcode;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _21MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode list = new ListNode(0);
        ListNode cursor = list;
        while (l1 != null && l2 != null) {
            ListNode temp;
            if (l1.val < l2.val) {
                temp = l1;
                l1 = l1.next;
            } else {
                temp = l2;
                l2 = l2.next;
            }
            temp.next = null;
            cursor.next = temp;
            cursor = cursor.next;
        }
        if (l1 != null) {
            cursor.next = l1;
        }else if (l2 != null) {
            cursor.next = l2;
        }

        return list.next;
    }
}
