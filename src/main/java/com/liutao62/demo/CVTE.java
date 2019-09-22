package com.liutao62.demo;

import com.liutao62.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CVTE {
    /**
     * 单链表 一次循环 返回中间结点。默认偶数返回前面节点  如 1->2 。返回1
     *
     * @param head
     * @return
     */
    public ListNode getMidNode(ListNode head) {
        if (head == null) return null;
        ListNode end = head, mid = head;
        while (end != null) {
            ListNode temp = end.next;
            if (temp != null) {
                end = temp.next;
                mid = mid.next;
            }else break;
        }
        return mid;
    }

    /**
     * 从置顶数组中获取五个随机的数，数组元素不重复，返回结果中也不能重复
     *
     * @param array
     * @return
     */
    public List<Integer> getRandomArray(int[] array) {
        if (array == null || array.length < 5) return null;
        int size = array.length;
        List<Integer> list = new ArrayList<>();
        Random rand = new Random();
        while (list.size() < 5){
            int index = rand.nextInt(size);
            list.add(array[index]);
            array[index] = array[--size];
        }
        return list;
    }
}
