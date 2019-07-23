package com.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortTest {
    private int[] array;
    Sort sort = new Sort();

    @Before
    public void setUp() throws Exception {
        array = new int[200000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (20000 * Math.random());
         //   System.out.print(array[i] + " ");
        }
       // System.out.println();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void bubbleSort() {
        sort.bubbleSort(array);
    }

    @Test
    public void selectedSort() {
        sort.selectedSort(array);
    }

    @Test
    public void insertSort() {
        sort.insertSort(array);
    }

    @Test
    public void shellSort() {
        sort.insertSort(array);
    }

    @Test
    public void heapSort() {
        sort.heapSort(array);
    }
}