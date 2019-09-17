package com.liutao62.leetcode;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 */
public class _121maxProfit {
    /**
     * T:O(n^2) S:O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int maxProfit = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int i1 = i + 1; i1 < prices.length; i1++) {
                int temp = prices[i1] - prices[i];
                if (temp > maxProfit) {
                    maxProfit = temp;
                }
            }
        }
        if (maxProfit > 0) return maxProfit;
        return 0;
    }

    /**
     * T:O(n)
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int maxProfit = Integer.MIN_VALUE;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        if (maxProfit > 0) return maxProfit;
        return 0;
    }
}
