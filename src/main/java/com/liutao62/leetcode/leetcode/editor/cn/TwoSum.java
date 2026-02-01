package com.liutao62.leetcode.leetcode.editor.cn;

import java.util.*;

public class TwoSum {
    public static void main(String[] args) {
        Solution solution = new TwoSum().new Solution();
        int[] ints = solution.twoSum(new int[]{-1, -2, -3, -4, -5}, -8);
        Arrays.stream(ints).forEach(System.out::println);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                Integer i1 = map.get(target - nums[i]);
                if (i1 != null) {
                    return new int[]{i1, i};
                }
                map.put(nums[i], i);
            }
            return null;

            // 回溯算法
//            List<Integer> result = new ArrayList<>();
//            backTracking(nums, 0, target, result);
//            return result.stream().mapToInt(Integer::intValue).toArray();
        }

        private List<Integer> backTracking(int[] nums, int index, int target, List<Integer> result) {
            if (result.size() > 2) {
                return null;
            }
            for (int i = index; i < nums.length; i++) {
                result.add(i);
                if (backTracking(nums, i + 1, target - nums[i], result) != null) {
                    return result;
                }
                result.remove((Object) i);
            }
            return target == 0 ? result : null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}