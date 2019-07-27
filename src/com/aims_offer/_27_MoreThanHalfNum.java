package com.aims_offer;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class _27_MoreThanHalfNum {
    public int MoreThanHalfNum_Solution(int[] array) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        for (int i : array) {
            if (map.get(i) == null) map.put(i, 1);
            else map.put(i, map.get(i) + 1);
            if (map.get(i) > array.length / 2) return i;
        }
        return 0;
    }
}
