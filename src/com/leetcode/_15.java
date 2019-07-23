package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 3) return lists;
        int one = 0, two = 1, three = 2;
        while (one < nums.length - 2) {
            if (nums[one] > 0) {
                break;
            }
            List<Integer> list = this.gerList(nums, one, two, three);
            if (list != null && !contains(lists, list)) lists.add(list);
            if (two == nums.length - 2) {
                two = ++one + 1;
                three = two + 1;
                continue;
            }
            if (three == nums.length - 1) three = ++two;
            three++;
        }
        return lists;
    }

    private List<Integer> gerList(int[] nums, int one, int two, int three) {
        if (nums[one] + nums[two] + nums[three] == 0) {
            List<Integer> list = new ArrayList<>(3);
            list.add(nums[one]);
            list.add(nums[two]);
            list.add(nums[three]);
            return list;
        }
        return null;
    }

    private boolean contains(List<List<Integer>> lists, List<Integer> list) {
        for (List<Integer> l : lists) {
            List<Integer> cloneL = (List<Integer>) ((ArrayList<Integer>) l).clone();
            List<Integer> cloneList = (List<Integer>) ((ArrayList<Integer>) list).clone();
            cloneL.removeAll(list);
            cloneList.removeAll(l);
            if (cloneL.size() == 0 && cloneList.size() == 0) return true;
        }
        return false;
    }
}
