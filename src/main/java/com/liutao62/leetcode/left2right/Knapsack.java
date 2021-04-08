package com.liutao62.leetcode.left2right;

import java.util.Random;

/**
 * @author liutao
 * @date Created in 2021/4/8 22:13
 * @description 从左往右的尝试模型：给定两个长度为 N 的数组 weights 和 values，分别代表 i 号货物的重量和价值。现有能载重 bag 的背包，
 * 求背包能够装下的最大价值
 */
public class Knapsack {

    public static void main(String[] args) {
        int n = 10;
        int bag = 50;
        int[] weights = new int[n];
        int[] values = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            weights[i] = random.nextInt(n);
            values[i] = random.nextInt(n);
        }

        for (int weight : weights) {
            System.out.print(weight + " ");
        }
        System.out.println();
        for (int value : values) {
            System.out.print(value + " ");
        }
        System.out.println();

        int process = process(weights, values, bag);
        System.out.println(process);
        int process2 = process2(weights, values, bag);
        System.out.println(process2);
        assert process == process2;
    }

    public static int process(int[] weights, int[] values, int bag) {
        // 假设自己有校验
        if (weights == null) {
            return 0;
        }
        return processHelper(weights, values, bag, 0, 0);
    }

    private static int processHelper(int[] weights, int[] values, int bag, int begin, int already) {
        // 超量了。说明当前方案无效
        if (already > bag) {
            return -1;
        }
        // 方案有效，还可以装货但是没货了
        if (begin == weights.length) {
            return 0;
        }

        int notChoose = processHelper(weights, values, bag, begin + 1, already);
        int choose = processHelper(weights, values, bag, begin + 1, already + weights[begin]);

        // 选择了并且当前方案有效
        if (choose != -1) {
            choose += values[begin];
        }

        return Math.max(notChoose, choose);
    }

    public static int process2(int[] weights, int[] values, int bag) {
        return processHelper2(weights, values, bag, 0);
    }

    // rest 剩余空间
    private static int processHelper2(int[] weights, int[] values, int rest, int begin) {
        if (rest < 0) {
            return -1;
        }
        // 方案有效，还可以装货但是没货了
        if (begin == weights.length) {
            return 0;
        }

        int notChoose = processHelper2(weights, values, rest, begin + 1);
        int choose = processHelper2(weights, values, rest - weights[begin], begin + 1);

        if (choose != -1) {
            choose += values[begin];
        }
        return Math.max(notChoose, choose);
    }
}
