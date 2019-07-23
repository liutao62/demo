package com.sort;

public class Sort {
    /**
     * @param
     * @return
     * @description 本方法只有一个参数，那就是待排序的array
     */
    public void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        // 下面，开始排序逻辑
        for (int j = array.length - 1; j > 0; j--) {
            swap(array, 0, j);
            adjustHeap(array, 0, j);
        }
    }

    /**
     * @param
     * @return
     * @description
     */
    public void adjustHeap(int[] array, int i, int length) {
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }
            // 如果发现子节点更大，则进行值的交换
            if (array[k] > temp) {
                swap(array, i, k);
                i = k;
            } else {
                break;
            }
        }
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a   元素的下标
     * @param b   元素的下标
     */
    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 冒泡排序基础版，O(n^2),T(1)
     *
     * @param array
     */
    public void bubbleSort(int[] array) {
        int temp;
        int i, i1;
        for (i = 0; i < array.length; i++) {
            for (i1 = i + 1; i1 < array.length; i1++) {
                if (array[i] > array[i1]) {
                    temp = array[i];
                    array[i] = array[i1];
                    array[i1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序，O(n^2) T(1),较冒泡排序而言减少了交换次数
     *
     * @param array
     */
    public void selectedSort(int[] array) {
        int index;
        int i, i1;
        for (i = 0; i < array.length; i++) {
            index = i;
            for (i1 = i + 1; i1 < array.length; i1++) {
                if (array[index] > array[i1]) {
                    index = i1;
                }
            }
            if (i != index) {
                int temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }
    }

    /**
     * 插入排序，O(n^2) T(1) 最好情况O(n),性能优于冒泡和选择
     *
     * @param array
     */

    public void insertSort(int[] array) {
        int temp;
        int i, i1;
        for (i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                temp = array[i];
                for (i1 = i; i1 > 0 && temp < array[i1 - 1]; i1--) {
                    array[i1] = array[i1 - 1];
                }
                array[i1] = temp;
            }
        }
    }

    /**
     * 希尔排序 O(nlogn),T(1)
     *
     * @param array
     */
    public void shellSort(int[] array) {
        int increment = array.length;
        int temp;
        int i, i1;
        do {
            increment = increment >> 1;
            for (i = increment; i < array.length; i++) {
                if (array[i] < array[i - increment]) {
                    temp = array[i];
                    for (i1 = i - increment; i1 > 0 && temp < array[i1]; i1 -= increment) {
                        array[i1 + increment] = array[i1];
                    }
                    array[i1 + increment] = temp;
                }
            }
        } while (increment > 1);
    }

    public void mergingSort(int[] array) {

    }
}
