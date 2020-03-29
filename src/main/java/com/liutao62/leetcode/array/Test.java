package com.liutao62.leetcode.array;

import java.util.Arrays;
import java.util.Random;

public class Test {
    public Integer[] getRandomArray(Integer[] arr,int len,int size) {
        int index = 0;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(len--);
            swap(arr, index, len);
        }
        return Arrays.copyOfRange(arr, len, len + size);
    }

    private void swap(Object[] arr, int i, int j) {
        Object tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }
}
