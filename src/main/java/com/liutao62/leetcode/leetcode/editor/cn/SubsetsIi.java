package com.liutao62.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubsetsIi {
    public static void main(String[] args) {
        Solution solution = new SubsetsIi().new Solution();
        List<List<Integer>> lists = solution.subsetsWithDup(new int[]{1, 2, 2});
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(Collections.emptyList());
            Arrays.sort(nums);
            boolean[] used = new boolean[nums.length];
            backtrack(result, new ArrayList<>(), nums, used, 0);
            return result;
        }

        private void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums, boolean[] used, int start) {
            if (start == nums.length) {
                return;
            }
            for (int i = start; i < nums.length; i++) {
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
                temp.add(nums[i]);
                result.add(new ArrayList<>(temp));
                used[i] = true;
                backtrack(result, temp, nums, used, i + 1);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}