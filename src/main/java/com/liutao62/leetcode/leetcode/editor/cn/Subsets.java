package com.liutao62.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        Solution solution = new Subsets().new Solution();
        List<List<Integer>> subsets = solution.subsets(new int[]{1, 2, 3});
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(Collections.emptyList());
            backtrack(nums, 0, res, new ArrayList<>());
            return res;
        }

        private void backtrack(int[] nums, int index, List<List<Integer>> res, List<Integer> subset) {
            for (int i = index; i < nums.length; i++) {
                subset.add(nums[i]);
                res.add(new ArrayList<>(subset));
                backtrack(nums, i + 1, res, subset);
                subset.remove((Object) nums[i]);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}