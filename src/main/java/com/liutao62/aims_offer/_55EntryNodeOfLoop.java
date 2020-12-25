package com.liutao62.aims_offer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2020/12/15 14:57
 * @description
 */
public class _55EntryNodeOfLoop {


    public ListNode EntryNodeOfLoop(ListNode pHead) {
        List<ListNode> list = new ArrayList<>();
        if (pHead == null) {
            return null;
        }
        while (pHead != null) {
            if (list.contains(pHead)) {
                return pHead;
            }
            list.add(pHead);
            pHead = pHead.next;
        }
        return null;
    }
}
