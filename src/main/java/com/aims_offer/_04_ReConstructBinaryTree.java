package com.aims_offer;

import java.util.HashMap;
import java.util.Map;

public class _04_ReConstructBinaryTree {

    public TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return build(pre, 0, pre.length - 1, 0, map);
    }

    private TreeNode build(int[] pre, int preStart, int preEnd, int inStart, Map<Integer, Integer> map) {
        if (preStart > preEnd) return null;
        Integer rootIdx = map.get(pre[preStart]);
        int leftLength = rootIdx - inStart;
        TreeNode root = new TreeNode(pre[preStart]);
        root.left = build(pre, preStart + 1, preStart + leftLength, inStart, map);
        root.right = build(pre, preStart + leftLength + 1, preEnd, rootIdx + 1, map);
        return root;
    }

    /**
     * 时间限制：1秒 空间限制：32768K 热度指数：467207
     * <p>
     * 题目描述
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
     * 则重建二叉树并返回。
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
