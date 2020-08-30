package com.liutao62.leetcode.string;

import java.util.*;

/**
 * @author liutao
 * @date Created in 2020/8/30 21:12
 * @description
 */
public class _09longestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Map<Integer, Integer> lenAndIndexMap = new TreeMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            if (str == null || str.length() == 0) {
                return "";
            }
            lenAndIndexMap.put(str.length(), i);
        }
        Object[] array = lenAndIndexMap.keySet().toArray();
        Arrays.sort(array);
        String minStr = strs[lenAndIndexMap.get(array[0])];

        int i = 0, j = 0;
        boolean flag = false;
        for (i = 0; i < minStr.length(); i++) {
            if (flag) {
                break;
            }
            char c = minStr.charAt(i);
            for (j = 0; j < strs.length; j++) {
                String str = strs[j];
                if (c == str.charAt(i)) {
                    continue;
                }
                flag = true;
                break;
            }
        }
        return flag && i != 0 ? minStr.substring(0, i - 1) : minStr.substring(0, i);
    }
}
