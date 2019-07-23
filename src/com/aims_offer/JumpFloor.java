package com.aims_offer;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class JumpFloor {
    public static int JumpFloor(int target) {
        return target >= 3 ? JumpFloor(target - 1) + JumpFloor(target - 2) :
                (target == 1 ? 1 : 2);
    }

    public static void main(String[] args) {
        System.out.println(JumpFloor(5));
    }
}
