package com.liutao62.leetcode;

import java.util.Arrays;

/**
 * @author liutao
 * @date Created in 2021/9/27 14:00
 * @description
 */
public class _567checkInclusion {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        char[] subString = s1.toCharArray();
        Arrays.sort(subString);
        char[] superString = s2.toCharArray();
        char[] temp = new char[subString.length];

        for (int i = 0; i < superString.length; i++) {
            if (s1.contains(String.valueOf(s2.charAt(i))) && inclusion(i, superString, subString, temp)) {
                return true;
            }
        }
        return false;
    }

    private boolean inclusion(int i, char[] superString, char[] subString, char[] temp) {
        if (superString.length - i < subString.length) {
            return false;
        }
        System.arraycopy(superString, i, temp, 0, temp.length);
        Arrays.sort(temp);

        for (int i1 = 0; i1 < subString.length; i1++) {
            if (subString[i1] != temp[i1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new _567checkInclusion().checkInclusion("ba", "eidbaooo"));
    }
}
