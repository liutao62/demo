package com.liutao62.leetcode.sort_and_search;

/**
 * @author liutao
 * @date Created in 2020/12/19 15:38
 * @description
 */
public class _01merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n <= 0) {
            return;
        }
        if (m <= 0) {
            System.arraycopy(nums2, 0, nums1, 0, nums2.length);
            return;
        }
        int idx1 = 0;
        for (int idx2 = idx1; idx2 < n; ) {
            if (idx1 < m) {
                if (nums1[idx1] > nums2[idx2]) {
                    System.arraycopy(nums1, idx1, nums1, ++idx1, ++m - idx1);
                    nums1[idx1 - 1] = nums2[idx2++];
                } else {
                    idx1++;
                }
            } else {
                System.arraycopy(nums2, idx2, nums1, idx1, n - idx2);
                break;
            }
        }
    }
}
