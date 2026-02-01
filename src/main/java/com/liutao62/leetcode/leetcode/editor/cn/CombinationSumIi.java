package com.liutao62.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumIi {
    public static void main(String[] args) {
        Solution solution = new CombinationSumIi().new Solution();
        for (List<Integer> integers : solution.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8)) {
            System.out.println(integers);
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            // 回溯 超内存使用了
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(candidates);
            backtrack(candidates, target, 0, new ArrayList<>(), res);
            return res;
        }

        private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
            if (target < 0) {
                return;
            }
            if (target == 0) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                int candidate = candidates[i];
                if (target < candidate) {
                    continue;
                }
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                path.add(candidate);
                backtrack(candidates, target - candidate, i + 1, path, res);
                path.remove((Object) candidate);
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}