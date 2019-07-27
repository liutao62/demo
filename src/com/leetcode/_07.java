package com.leetcode;

public class _07 {
    public int reverse(int x) {
        if (x == -2147483648) return 0;
        StringBuilder sb = new StringBuilder();
        boolean is = x >= 0;
        if (!is) x = -x;
        while (x > 9) {
            int temp = x % 10;
            if (temp == 0 && sb.length() == 0) {
            } else sb.append(temp);
            x /= 10;
        }
        sb.append(x);
        Long value = Long.valueOf(sb.toString());
        if (is && value > Math.pow(2, 31) - 1) return 0;
        else if (is) return Math.toIntExact(value);
        else if (!is && value > Math.pow(2, 31)) return 0;
        else return -Math.toIntExact(value);
    }
}
