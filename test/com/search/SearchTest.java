package com.search;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchTest {

    private int[] array = new int[]{1, 3, 4, 5, 6, 7, 11, 21, 22};
    private Search search = new Search();

    @Test
    public void binarySearch() {
        Assert.assertEquals(search.binarySearch(array, 5), 3);
        Assert.assertEquals(search.binarySearch(array, 8), -1);
        Assert.assertEquals(search.binarySearch(array, 22), 8);
        Assert.assertEquals(search.binarySearch(array, 23), -1);
        Assert.assertEquals(search.binarySearch(array, -2), -1);
    }

    @Test
    public void InterpolationSearch() {
        Assert.assertEquals(search.binarySearch(array, 5), 3);
        Assert.assertEquals(search.binarySearch(array, 8), -1);
        Assert.assertEquals(search.binarySearch(array, 22), 8);
        Assert.assertEquals(search.binarySearch(array, 23), -1);
        Assert.assertEquals(search.binarySearch(array, -2), -1);
    }
}