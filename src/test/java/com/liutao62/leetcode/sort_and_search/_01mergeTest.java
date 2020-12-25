package com.liutao62.leetcode.sort_and_search;

import org.junit.Test;

import java.util.Arrays;

/**
 * [1,2,3,0,0,0]
 * 3
 * [2,5,6]
 * 3
 */
public class _01mergeTest {
    _01merge merge = new _01merge();

    @Test
    public void test() {
        int[] num1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] num2 = new int[]{2, 5, 6};
        merge.merge(num1, 3, num2, 3);
        Arrays.stream(num1).forEach(System.out::print);
    }
}