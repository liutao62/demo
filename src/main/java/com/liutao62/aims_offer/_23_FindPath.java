package com.liutao62.aims_offer;

import java.util.ArrayList;

/**
 * 输入一颗二叉树的跟节点和一个整数，
 * 打印出二叉树中结点值的和为输入整数的所有路径。
 * <p>
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class _23_FindPath {
    ArrayList<ArrayList<Integer>> pathList = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> path = new ArrayList<Integer>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null)
            return pathList;
        path.add(root.val);
        if (root.left == null && root.right == null && target == root.val) {
            pathList.add(new ArrayList<Integer>(path));
        }
        if (root.val <= target && root.left != null) {
            FindPath(root.left, target - root.val);
        }
        if (root.val <= target && root.right != null) {
            FindPath(root.right, target - root.val);
        }
        //如果没有递归则 当前路径不能满足target。移除最后添加的节点返回到父节点
        path.remove(path.size() - 1);
        return pathList;
    }
}
