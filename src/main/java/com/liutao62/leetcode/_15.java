package com.liutao62.leetcode;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */

/**
 * 超出时间限制
 */
public class _15 {

    public static void main(String[] args) {
        int[] array = Lists.newArrayList(-1, 0, 1).stream().mapToInt(Integer::intValue).toArray();
        List<List<Integer>> lists = new _15().threeSum(array);
        System.out.println(lists);
    }

    private static final Set<List<Integer>> threeSum = new HashSet<>();

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        if (nums == null || nums.length < 3) {
            return Collections.EMPTY_LIST;
        }

        int begin = 0, end = nums.length - 1, mid = (begin + end) >> 1;
        List<List<Integer>> lists = new ArrayList<>();
        findList(begin, end, mid, nums, lists, false, false);
        return lists;
    }

    private void findList(int begin, int end, int mid, int[] nums, List<List<Integer>> lists, boolean midIncrease, boolean midReduce) {
        int beginNum = nums[begin];
        int endNum = nums[end];
        int midNum = nums[mid];
        if (mid > begin && mid < end) {
            int value = beginNum + midNum + endNum;
            if (value == 0) {
                List<Integer> triplet = new ArrayList<>(3);
                triplet.add(beginNum);
                triplet.add(midNum);
                triplet.add(endNum);
                if (!threeSum.contains(triplet)) {
                    lists.add(triplet);
                    threeSum.add(triplet);
                }
                findList(begin + 1, end, mid, nums, lists, false, false);
                findList(begin, end - 1, mid, nums, lists, false, false);
            } else if (value > 0) {
                findList(begin, end - 1, mid, nums, lists, false, false);
                if (!midIncrease) {
                    findList(begin, end, mid - 1, nums, lists, false, true);
                }
            } else {
                findList(begin + 1, end, mid, nums, lists, false, false);
                if (!midReduce) {
                    findList(begin, end, mid + 1, nums, lists, true, false);
                }
            }
        }
    }

}
