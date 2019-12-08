package com.liutao62.leetcode.array;

public class _02maxProfit {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int total = 0;
        int lastPrice = prices[0];
        int buyPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < lastPrice){
                total += lastPrice - buyPrice;
                buyPrice = prices[i];
            }
            lastPrice = prices[i];
        }
        if (lastPrice > buyPrice) {
            total += lastPrice - buyPrice;
        }
        return total;
    }
}
