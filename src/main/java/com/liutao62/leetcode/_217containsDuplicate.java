package com.liutao62.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _217containsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int len = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
            if (set.size() != i + 1) return true;
        }
        return false;
    }
}
