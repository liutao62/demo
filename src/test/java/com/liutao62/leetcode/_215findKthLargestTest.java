package com.liutao62.leetcode;

import org.junit.Test;

public class _215findKthLargestTest {

    private _215findKthLargest test = new _215findKthLargest();
    private int[] array = new int[]{3, 2, 1, 5, 6,};
    private int target = 2;

    @Test
    public void findKthLargest() {
        test.findKthLargest(array, target);
    }
}