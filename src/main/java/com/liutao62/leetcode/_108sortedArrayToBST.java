package com.liutao62.leetcode;

public class _108sortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return nums == null ? null : buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int l, int h) {
        if (l > h) return null;
        int mid = (l + h) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums, l, mid - 1);
        root.right = buildTree(nums, mid + 1, h);
        return root;
    }
}
