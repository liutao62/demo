package com.liutao62.leetcode.array;

import java.util.LinkedList;

public class _07plusOne {
    public int[] plusOne(int[] digits) {
        boolean flag = true;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = digits.length - 1; i >= 0; i--) {
            if (flag) {
                if (digits[i] == 9) {
                    list.addFirst(0);
                } else {
                    list.addFirst(digits[i] + 1);
                    flag = false;
                }
            } else {
                list.addFirst(digits[i]);
            }
        }
        if (flag){
            list.addFirst(1);
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}
