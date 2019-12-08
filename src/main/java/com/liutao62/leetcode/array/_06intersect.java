package com.liutao62.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class _06intersect {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        for (int i : nums1) {
            list.add(i);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (list.contains(nums2[i]) && list.remove(Integer.valueOf(nums2[i]))) {
                result.add(nums2[i]);
            }
        }
        int[] arr1 = result.stream().mapToInt(Integer::valueOf).toArray();
        return arr1;
    }
}
