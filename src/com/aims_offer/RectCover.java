package com.aims_offer;

/**
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
public class RectCover {
    public static int RectCover(int target) {
        return target == 0 ? 0 : (target >= 3 ? RectCover(target - 1) + RectCover(target - 2) :
                (target == 1 ? 1 : 2));
    }

    public static void main(String[] args) {
        System.out.println(RectCover(4));
    }
}
