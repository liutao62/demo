package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author liutao
 * @date Created in 2021/4/7 22:33
 * @description 从左往右的尝试模型：字符串子序列
 */
public class AllSubSequence {

    public static void main(String[] args) {
        String str = "abcdd";
        List<String> subString = new ArrayList<>();
        String path = "";
        process(str.toCharArray(), 0, path, subString);
        System.out.println(subString);

        Set<String> set = new HashSet<>();
        processNoRepeat(str.toCharArray(), 0, path, set);
        System.out.println(set);
    }

    /**
     * @param chars
     * @param index
     * @param path
     * @param subString
     * @description 获得所有子序列
     */
    private static void process(char[] chars, int index, String path, List<String> subString) {
        if (index == chars.length) {
            subString.add(path);
            return;
        }
        String optionNo = path;
        process(chars, index + 1, optionNo, subString);
        String optionYes = path + String.valueOf(chars[index]);
        process(chars, index + 1, optionYes, subString);
    }

    /**
     * @param chars
     * @param index
     * @param path
     * @param subString
     * @description 获得所有不重复的子序列
     */
    private static void processNoRepeat(char[] chars, int index, String path, Set<String> subString) {
        if (index == chars.length) {
            subString.add(path);
            return;
        }
        String optionNo = path;
        processNoRepeat(chars, index + 1, optionNo, subString);
        String optionYes = path + String.valueOf(chars[index]);
        processNoRepeat(chars, index + 1, optionYes, subString);
    }
}
