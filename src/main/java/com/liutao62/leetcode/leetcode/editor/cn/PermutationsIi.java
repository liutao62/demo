package com.liutao62.leetcode.leetcode.editor.cn;

import java.util.*;

public class PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new PermutationsIi().new Solution();
        for (List<Integer> integers : solution.permuteUnique(new int[]{1, 1, 2, 2})) {
            System.out.println(integers);
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            boolean[] used = new boolean[nums.length];
            Arrays.sort(nums);
            backtrack(res, new ArrayList<>(), nums, used);
            return res;
        }

        private void backtrack(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] used) {
            if (temp.size() == nums.length) {
                res.add(new ArrayList<>(temp));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) continue;
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
                int num = nums[i];
                used[i] = true;
                temp.add(num);
                backtrack(res, temp, nums, used);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}