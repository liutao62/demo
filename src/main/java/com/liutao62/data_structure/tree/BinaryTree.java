package com.liutao62.data_structure.tree;

import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author liutao
 * @date Created in 2021/3/10 23:07
 * @description
 */
@Data
public class BinaryTree {
    private int val;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(int val) {
        this.val = val;
    }

    public static class BinaryTreeBuilder {


        public static BinaryTree build(int[] pre, int[] in) {
            if (pre == null || in == null) return null;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < in.length; i++) {
                map.put(in[i], i);
            }
            return buildHelper(pre, 0, pre.length - 1, 0, map);
        }

        private static BinaryTree buildHelper(int[] pre, int preStart, int preEnd, int inStart, Map<Integer, Integer> map) {
            if (preStart > preEnd) return null;
            int data = pre[preStart];
            Integer rootIdx = map.get(data);
            int leftLength = rootIdx - inStart;
            BinaryTree root = new BinaryTree(data);
            root.left = buildHelper(pre, preStart + 1, preStart + leftLength, inStart, map);
            root.right = buildHelper(pre, preStart + leftLength + 1, preEnd, rootIdx + 1, map);
            return root;
        }


    }


    public static void preOrder(BinaryTree root) {
        if (root == null) {
            return;
        }
        LinkedList<BinaryTree> stack = new LinkedList<>();
        BinaryTree treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                System.out.println(treeNode.getVal());
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.right;
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree build = BinaryTreeBuilder.build(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{3, 2, 4, 1, 6, 5, 7});

        BinaryTree.preOrder(build);
    }
}
