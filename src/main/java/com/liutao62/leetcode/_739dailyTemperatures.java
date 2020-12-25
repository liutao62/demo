package com.liutao62.leetcode;

/**
 * @author liutao
 * @date Created in 2020/12/19 16:34
 * @description
 */
public class _739dailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) {
            return T;
        }
        int[] dayNum = new int[T.length];

        int i = 0, j = i + 1;
        for (; i < T.length; i++) {
            j = i + 1;
            for (; j < T.length; j++) {
                if (T[i] < T[j]) {
                    dayNum[i] = j - i;
                    break;
                }
            }
        }
        return dayNum;
    }

    public static void main(String[] args) {
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] ints = new _739dailyTemperatures().dailyTemperatures(T);
    }
}
