
package com.liutao62.leetcode.leetcode.editor.cn;

import java.util.Arrays;

public class MinimumNumberOfOperationsToConvertTime {
    public static void main(String[] args) {
        Solution solution = new MinimumNumberOfOperationsToConvertTime().new Solution();
        int i = solution.convertTime("02:30", "04:35");
        System.out.println(i);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int convertTime(String current, String correct) {
            java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("HH:mm");
            try {
                int l = (int) ((simpleDateFormat.parse(correct).getTime() - simpleDateFormat.parse(current).getTime()) / 60000);
                return getCount(l);
            } catch (java.text.ParseException e) {
                return -1;
            }
        }

        private int getCount(int l) {
            if (l == 0) {
                return 0;
            }
            int[] nums = {1, 5, 15, 60};
            int[] dp = new int[l + 1];
            Arrays.fill(dp, -1);
            return dp(l, nums, dp);
        }

        private int dp(int l, int[] nums, int[] dp) {
            if (l == 0) {
                return 0;
            }
            if (l < 0) {
                return -1;
            }
            if (dp[l] != -1) {
                return dp[l];
            }
            int num = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                int dp1 = dp(l - nums[i], nums, dp);
                if (dp1 == -1) continue;
                num = Math.min(num, dp1 + 1);
            }
            num = num == Integer.MAX_VALUE ? 0 : num;
            dp[l] = num;
            return num;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}