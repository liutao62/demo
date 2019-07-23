package com.aims_offer;

import java.util.HashMap;
import java.util.Map;

public class ReConstructBinaryTree {

    public static void main(String[] args) {
        int[] pre = new int[]{1, 2, 4, 7, 3, 5, 6, 8};

        int[] in = new int[]{4, 7, 2, 1, 5, 3, 8, 6};

        ReConstructBinaryTree tree = new ReConstructBinaryTree();
        TreeNode node = tree.buildTree(pre, in);
        tree.pre(node);
    }
    public TreeNode buildTree(int[] pre, int[] in){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i],i);
        }
        return buildBinaryTree(pre,0,pre.length,map);
    }

    private TreeNode buildBinaryTree(int[] pre,int beginIndex,int childLength, Map<Integer,Integer> map) {
        if (pre == null) return null;
        if (childLength <= 0) return null;
        TreeNode root = new TreeNode(pre[beginIndex]);
        root.left = buildBinaryTree(pre,beginIndex + 1,map.get(pre[beginIndex]),map);
        root.right = buildBinaryTree(pre,map.get(pre[beginIndex]) + 1,pre.length - map.get(pre[beginIndex]),map);
        return root;
    }

    public void pre(TreeNode node){
        if (node != null){
            System.out.println(node.val);
            pre(node.left);
            pre(node.right);
        }
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


    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i],i);
        }
        return build(pre,0,pre.length - 1,0,map);
    }
    public TreeNode build(int[] pre,int startIndex,int endIndex,int rightStart, Map<Integer,Integer> map){
        if(startIndex > endIndex) return null;
        TreeNode root = new TreeNode(pre[startIndex]);
        int rootIndex = map.get(pre[startIndex]);
        int leftLength = rootIndex - rightStart;
        root.left = build(pre,startIndex + 1,startIndex + leftLength,rightStart,map);
        root.right = build(pre,startIndex + leftLength + 1,endIndex,rootIndex + 1,map);
        return root;
    }

}
