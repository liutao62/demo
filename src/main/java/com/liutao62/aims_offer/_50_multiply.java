package com.liutao62.aims_offer;

/**
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 */
public class _50_multiply {
    public int[] multiply(int[] A) {
        if (A == null || A.length == 0) return null;
        int[] b = new int[A.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = 1;
            for (int i1 = 0; i1 < A.length; i1++) {
                if (i == i1) continue;
                b[i] *= A[i1];
            }
        }
        return b;
    }
}
