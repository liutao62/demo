package com.liutao62.leetcode;

import org.junit.Test;

public class _169majorityElementTest {
    private _169majorityElement test = new _169majorityElement();

    @Test
    public void majorityElement() {
        System.out.println(test.majorityElement(new int[]{1, 2, 3, 1, 1, 3, 1}));
        assert 1 == test.majorityElement(new int[]{1, 2, 3, 1, 1, 3, 1});
    }
}