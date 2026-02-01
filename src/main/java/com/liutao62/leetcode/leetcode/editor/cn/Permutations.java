package com.liutao62.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        Solution solution = new Permutations().new Solution();
        List<List<Integer>> permute = solution.permute(new int[]{1, 2, 3});
        for (List<Integer> integers : permute) {
            System.out.println(integers);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            boolean[] used = new boolean[nums.length];
            backtrack(nums, res, new ArrayList<>(), used);
            return res;
        }

        private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] used) {
            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) continue;
                used[i] = true;
                path.add(nums[i]);
                backtrack(nums, res, path, used);
                path.remove((Object) nums[i]);
                used[i] = false;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}