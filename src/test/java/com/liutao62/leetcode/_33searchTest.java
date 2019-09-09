package com.liutao62.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class _33searchTest {
    private _33search test = new _33search();

    @Test
    public void search() {
        Assert.assertEquals(1, test.search(new int[]{1,3}, 3));
    }
}