package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class _04 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        ArrayList<Integer> list = new ArrayList<>(128);
        if (nums1 != null || nums1.length > 0) {
            for (int i : nums1) {
                list.add(i);
            }
        }
        if (nums2 != null || nums2.length > 0) {
            for (int i : nums2) {
                list.add(i);
            }
        }
        Collections.sort(list);
        if (list.size() == 1) return list.get(0) / 1.0;
        int index = list.size() >> 1;
        return list.size() % 2 == 0 ? (list.get(index) + list.get(index - 1))
                / 2.0 : list.get(index);
    }
}
