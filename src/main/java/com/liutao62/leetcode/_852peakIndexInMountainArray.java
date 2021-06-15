package com.liutao62.leetcode;

/**
 * @author liutao
 * @date Created in 2021/6/15 23:00
 * @description
 */
public class _852peakIndexInMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        int begin = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (begin > arr[i]) {
                return i - 1;
            }
            begin = arr[i];
        }
        return -1;


        // int left = 0, right = arr.length - 1, mid;
        // int leftMid, rightMid;
        // int leftMidValue, rightMidValue;
        // while (left < right) {
        //     mid = (left + right) >> 1;
        //     leftMid = (left + mid) >> 1;
        //     rightMid = (right + mid) >> 1;
        //     leftMidValue = arr[leftMid];
        //     rightMidValue = arr[rightMid];
        //
        //     if (rightMidValue > arr[right]) {
        //         if (rightMidValue < arr[rightMid + 1]) {
        //             left = rightMid;
        //             continue;
        //         }
        //         if (leftMidValue > arr[left] && leftMidValue > arr[leftMid - 1]) {
        //             left = leftMid;
        //             right = rightMid;
        //         } else {
        //             right = leftMid;
        //         }
        //     } else {
        //         left = rightMid;
        //     }
        // }
        // return left;

    }

    public static void main(String[] args) {
        int i = new _852peakIndexInMountainArray().peakIndexInMountainArray(new int[]{69, 100, 99, 79, 78, 67, 36, 26, 19});
        System.out.println(i);
    }
}
