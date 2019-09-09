package com.liutao62.leetcode;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _23mergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode newNode = new ListNode(0);
        ListNode cursor = newNode;
        while (true){
            int min = Integer.MAX_VALUE;
            int index = -1;
            int len = lists.length;
            for (int i = 0; i < lists.length; i++) {
                ListNode node = lists[i];
                if (node != null && node.val < min){
                    min = node.val;
                    index = i;
                }else if (node == null) len--;
            }
            // 提前判断避免后续操作空指针异常
            if (len == 0){
                break;
            }
            if (index > -1){
                cursor.next = lists[index];
                lists[index] = lists[index].next;
                cursor.next.next = null;
                cursor = cursor.next;
            }
        }

        return newNode.next;
    }
}
