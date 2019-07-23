package com.aims_offer;

/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，
 * 一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * <p>
 * 返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class Clone {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;
        RandomListNode currentNode = pHead;
        RandomListNode newHead = new RandomListNode(currentNode.label);
        while (currentNode != null){
            if (currentNode.next != null)
                newHead.next = new RandomListNode(currentNode.next.label);
            currentNode = currentNode.next;
            
        }
        return null;
    }
}

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
