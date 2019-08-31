package com.sort;

public class Sort {
    /**
     * @param
     * @return
     * @description 待排序的array
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
     * @param a
     * @param b
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

    public void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int middle = (low + high) >> 1;
        mergeSort(array, low, middle);
        mergeSort(array, middle + 1, high);
        merge(array, low, middle, high);
    }

    private void merge(int[] array, int low, int middle, int high) {
        // 辅助数组
        int[] helper = new int[array.length];

        System.arraycopy(array, 0, helper, 0, high + 1);

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        while (helperLeft <= middle && helperRight <= high) {
            if (helper[helperLeft] <= helper[helperRight]) {
                array[current++] = helper[helperLeft++];
            } else {
                array[current++] = helper[helperRight++];
            }
        }

        int remaining = middle - helperLeft;
        System.arraycopy(helper, helperLeft, array, current, remaining + 1);
    }

    public void quickSort(int[] array) {
        if (array == null || array.length == 0) return;
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int index = partition(array, left, right);
            quickSort(array, left, index - 1);
            quickSort(array, index + 1, right);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int temp = 0;
        for (int i = left; i < right; i++) {
            //加入 left ！= i 比较条件，避免不必要的代码执行，但也增加了条件语句的复杂性，
            //在 数组大小为 1亿 的情况下 比无此比较条件总体缩减 0 - 3 s
            if (array[i] < pivot && left != i) {
                temp = array[i];
                array[i] = array[left];
                array[left] = temp;
                left++;
            }
        }
        temp = array[left];
        array[left] = array[right];
        array[right] = temp;
        return left;
    }
}
