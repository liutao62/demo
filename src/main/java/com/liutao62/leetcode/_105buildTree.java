package com.liutao62.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liutao
 * @date Created in 2021/10/25 22:39
 * @description
 */
public class _105buildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        Integer rootIndex = inMap.get(preorder[0]);
        return buildTree(0, preorder.length - 1, 0, preorder, inMap);
    }

    private TreeNode buildTree(int preStart, int preEnd, int inStart, int[] pre, Map<Integer, Integer> inMap) {
        if (preStart > preEnd) {
            return null;
        }
        int rootIdx = inMap.get(pre[preStart]);
        int leftLength = rootIdx - inStart;
        TreeNode root = new TreeNode(pre[preStart]);
        root.left = buildTree(preStart + 1, preStart + leftLength, inStart, pre, inMap);
        root.right = buildTree(preStart + leftLength + 1, preEnd, rootIdx + 1, pre, inMap);
        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new _105buildTree().buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(treeNode);
    }
}
