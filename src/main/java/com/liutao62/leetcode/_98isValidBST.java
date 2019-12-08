package com.liutao62.leetcode;

import com.liutao62.aims_offer._25Convert;

import java.util.LinkedList;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @see _25Convert
 */
public class _98isValidBST {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        LinkedList<TreeNode> list = new LinkedList<>();
        inOrder(root, list);
        TreeNode last = list.poll();
        for (TreeNode node : list) {
            if (node.val > last.val) {
                last = node;
            }else return false;
        }
        return true;
    }

    public void inOrder(TreeNode root, LinkedList<TreeNode> list) {
        if (root != null) {
            inOrder(root.left, list);
            list.add(root);
            inOrder(root.right, list);
        }
    }
}
