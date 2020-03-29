package com.liutao62.leetcode.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class _07strStrTest {

    _07strStr test = new _07strStr();
    @Test
    public void kmp() {
        test.kmp("abaabaabcabbcbac","aabc");
    }
}