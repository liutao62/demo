package com.liutao62.leetcode.scope;

/**
 * @author liutao
 * @date Created in 2021/4/8 23:44
 * @description 范围上的尝试模型：一副打乱顺序的明牌，A B 都是绝顶聪明的人，问能拿到的最大分数是。
 */
public class CardsInLine {

    // arr :
    public int max(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(
                // 必有一个人先手
                first(arr, 0, arr.length - 1),
                // 必有一人后手
                after(arr, 0, arr.length - 1));
    }

    // 后手
    private int after(int[] arr, int left, int right) {
        // 只有一张牌，后手的话对手会拿
        if (left == right) {
            return 0;
        }
        // 选择下一次先手最小的值，对手聪明绝顶肯定不可能让你拿到大的值啊
        return Math.min(
                // 对手选 left
                first(arr, left + 1, right),
                // 对手选 right
                first(arr, left, right + 1));
    }

    // 先手
    private int first(int[] arr, int left, int right) {
        // 只有一张牌
        if (left == right) {
            return arr[left];
        }
        // 选择两种方案里面最大的值
        return Math.max(
                // 选左边并加上后手能拿到的最大的值
                arr[left] + after(arr, left + 1, right),
                // 选右边并加上后手能拿到的最大的值
                arr[right] + after(arr, left, right - 1));
    }

}
