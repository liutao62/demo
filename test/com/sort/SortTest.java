package com.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class SortTest {

    Sort sort = new Sort();
    int size = 40000000;
    private int[] array = new int[size];

    @Before
    public void setUp() throws Exception {
        Random rand = new Random();
        for (int i = 0; i< size; i++) {
            array[i] = rand.nextInt(size);
        }
    }

/*
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
*/

    @Test
    public void quickSort(){sort.quickSort(array);}

}