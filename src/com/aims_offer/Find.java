package com.aims_offer;

/*
    时间限制：1秒 空间限制：32768K 热度指数：912656
本题知识点： 查找

题目描述
    在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
    每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
    判断数组中是否含有该整数。
 */
public class Find {
    public boolean Find(int target, int[][] array) {
        int outHigh = array.length - 1, inHigh = 0, outLow = 0, inLow = 0;
        int outKey = 0, inKey = 0;
        while (outKey < array.length) {
            inHigh = array[outKey].length - 1;
            inLow = 0;
            inKey = inHigh;
            while (inKey >= 0) {
                inKey = (inHigh + inLow) / 2;
                if (inHigh - inKey == 1 && inLow + 1 == inKey
                        || inHigh == inKey || inKey == inLow) {
                    break;
                }
                if (array[outKey][inHigh] < target) {
                    break;
                }
                if (array[outKey][inHigh] == target) {
                    return true;
                }
                if (array[outKey][inLow] > target) {
                    return false;
                }
                if (array[outKey][inLow] == target) {
                    return true;
                }
                if (array[outKey][inKey] > target) {
                    inHigh = inKey;
                } else if (array[outKey][inKey] < target) {
                    inLow = inKey;
                } else if (array[outKey][inKey] == target) {
                    return true;
                }
            }
            outKey++;
            if (outKey >= array.length) {
                break;
            }
        }
        return false;
    }
}
