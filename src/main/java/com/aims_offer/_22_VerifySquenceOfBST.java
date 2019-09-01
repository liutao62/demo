package com.aims_offer;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class _22_VerifySquenceOfBST {
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length <= 0) return false;
        return verifySquenceOfBST(sequence,0,sequence.length - 1);
    }

    public boolean verifySquenceOfBST(int[] sequence,int beginIndex
            ,int endIndex) {
        if (beginIndex + 1 >= endIndex) return true;
        int rootValue = sequence[endIndex];
        int rightBeginIndex = endIndex;
        for (int i = beginIndex; i <= endIndex; i++) {
            if (sequence[i] > rootValue){
                rightBeginIndex = i;
                break;
            }
        }
        for (int i = beginIndex; i < endIndex; i++) {
            if (i < rightBeginIndex){
                if (sequence[i] > rootValue) return false;
            }else {
                if (sequence[i] < rootValue) return false;
            }
        }
        return verifySquenceOfBST(sequence,beginIndex,rightBeginIndex - 1)
                && verifySquenceOfBST(sequence,rightBeginIndex,endIndex - 1);
    }
}
