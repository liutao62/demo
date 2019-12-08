package com.liutao62.leetcode;

public class _122maxProfit {
    public int maxProfit(int[] prices) {
        int sum = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            int value = prices[i];

            if (min == Integer.MAX_VALUE) {
                min = value;
            } else {
                if (prices[i] < min && max == Integer.MIN_VALUE) {
                    min = value;
                } else if (value > max) {
                    max = value;
                } else {
                    sum += max - min;
                    max = Integer.MIN_VALUE;
                    min = value;
                }

            }
        }
        if (min != Integer.MAX_VALUE && max != Integer.MIN_VALUE) sum += max - min;

        return sum;
    }
}
