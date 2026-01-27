package com.liutao62.leetcode;

import java.util.Arrays;

public class _322coinChange {

    public static void main(String[] args) {
        new _322coinChange().coinChange(new int[]{1, 2, 5}, 11);
    }
    public static int coinChange(int[] coins, int amount) {
        // 备忘录，自顶向下
//        Arrays.sort(coins);
//        int num = 0;
//        for (int i = coins.length - 1; i >= 0; i--) {
//            if (amount == 0) {
//                break;
//            }
//            if (amount > coins[i]) {
//                num = amount / coins[i] + num;
//                amount = amount % coins[i];
//            }
//        }
//        if (amount != 0) {
//            return -1;
//        }
//        return num;

        // dp 自底向上
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
