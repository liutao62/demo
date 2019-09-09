package com.liutao62.aims_offer;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 */
public class _39_FindNumsAppearOnce {
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        java.util.HashSet<Integer> set = new java.util.HashSet<>();
        for (int i : array) {
            if (set.contains(i)) set.remove(i);
            else set.add(i);
        }
        java.util.Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            if (set.size() == 2) {
                num1[0] = iterator.next();
                iterator.remove();
            } else num2[0] = iterator.next();
        }
    }
}
