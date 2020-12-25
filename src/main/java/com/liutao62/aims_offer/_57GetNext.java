package com.liutao62.aims_offer;

/**
 * @author liutao
 * @date Created in 2020/12/15 15:45
 * @description 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class _57GetNext {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        TreeLinkNode node = inOrder(pNode);
        return null;
    }

    private TreeLinkNode inOrder(TreeLinkNode pNode) {
        return pNode.left == null ? pNode : inOrder(pNode.left);
    }
}
