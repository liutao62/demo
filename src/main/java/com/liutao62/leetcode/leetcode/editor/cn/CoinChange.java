//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 2³¹ - 1 
// 0 <= amount <= 10⁴ 
// 
//
// Related Topics 广度优先搜索 数组 动态规划 👍 3184 👎 0


package com.liutao62.leetcode.leetcode.editor.cn;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        Solution solution = new CoinChange().new Solution();
        int i = solution.coinChange(new int[]{186, 419, 83, 408}, 6249);
        System.out.println(i);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int coinChange(int[] coins, int amount) {
            if (amount == 0) return 0;
            Arrays.sort(coins);
            if (amount < coins[0]) return -1;

            int[] dp = new int[amount + 1];
            Arrays.fill(dp, -2);
            return dp(coins, amount, dp);
        }

        private int dp(int[] coins, int amount, int[] dp) {
            if (amount == 0) return 0;
            if (amount < 0) return -1;
            // 1、避免子任务重复计算
            if (dp[amount] != -2) {
                return dp[amount];
            }
            // 2、子任务计算
            int num = Integer.MAX_VALUE;
            for (int i = coins.length - 1; i >= 0; i--) {
                int coin = coins[i];
                if (coin > amount) continue;
                int j = dp(coins, amount - coin, dp);
                if (j == -1) {
                    continue;
                }
                if (j == 0) {
                    return 1;
                }
                num = Math.min(num, j + 1);
            }
            num = num == Integer.MAX_VALUE ? -1 : num;
            return dp[amount] = num;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}