package com.liutao62.leetcode;

import java.util.Arrays;

public class _322coinChange {
    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int num = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            if (amount == 0) {
                break;
            }
            if (amount > coins[i]) {
                num = amount / coins[i] + num;
                amount = amount % coins[i];
            }
        }
        if (amount != 0) {
            return -1;
        }
        return num;
    }
}
