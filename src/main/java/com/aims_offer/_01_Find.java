package com.aims_offer;

/*
    时间限制：1秒 空间限制：32768K 热度指数：912656
本题知识点： 查找

题目描述
    在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
    每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
    判断数组中是否含有该整数。
 */
public class _01_Find {
    public boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0) return false;
        int low, high;
        for (int i = 0; i < array.length; i++) {
            high = array[i].length - 1;
            low = 0;
            int mid = (high + low) >> 1;
            while (high >= low) {
                if (target == array[i][mid]) return true;
                else if (target > array[i][mid]) {
                    low = mid + 1;
                } else high = mid - 1;
                mid = (high + low) >> 1;
            }
        }
        return false;
    }
}
