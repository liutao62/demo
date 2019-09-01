package com.leetcode;

public class _06 {
    public String convert(String s, int numRows) {
        if (s == null || s.trim().length() == 0) return "";
        if (numRows == 1) return s;
        Character[][] chars = new Character[numRows][s.length()];
        int row = 0, cos = 0;
        int i = 0;
        while (i < s.length()) {
            if (row == numRows - 1) {
                while (row > 0 && i < s.length()) {
                    chars[row--][cos++] = s.charAt(i++);
                }
            } else {
                chars[row++][cos] = s.charAt(i++);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < chars.length; i++) {
            for (int i1 = 0; i1 < chars[i].length; i1++) {
                if (chars[i][i1] != null) {
                    sb.append(chars[i][i1]);
                }
            }
            System.out.println();
        }
        return sb.toString();
    }
}
