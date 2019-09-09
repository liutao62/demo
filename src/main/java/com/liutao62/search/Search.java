package com.liutao62.search;

public class Search {

    /**
     * 二分查找，平均性能较好
     *
     * @param array
     * @param key
     * @return
     */
    public int binarySearch(int[] array, int key) {
        if (array == null || array.length == 0) return -1;
        int high = array.length, low = 0;
        int mid = 0;
        while (high >= low) {
            mid = (high + low) >> 1;
            if (array[mid] > key) high = mid - 1;
            else if (array[mid] < key) low = mid + 1;
            else return mid;
        }
        return -1;
    }

    /**
     * 插值查找，表长较大，分布较均匀，查询效率较高
     *
     * @param array
     * @param key
     * @return
     */
    public int InterpolationSearch(int[] array, int key) {
        if (array == null || array.length == 0) return -1;
        int high = array.length, low = 0;
        int mid = 0;
        while (high >= low) {
            mid = low + (key - array[low]) * (high - low) / (array[high] - array[low]);
            if (array[mid] > key) high = mid - 1;
            else if (array[mid] < key) low = mid + 1;
            else return mid;
        }
        return -1;
    }

    public int FibonacciSearch(int[] array[], int key) {


        return -1;
    }
}
