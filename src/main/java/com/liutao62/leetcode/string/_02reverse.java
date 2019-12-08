package com.liutao62.leetcode.string;

public class _02reverse {
    public int reverse(int x) {
        long tem = 0;
        boolean flag = false;
        if (x < 0) {
            flag = true;
        }
        String s = String.valueOf(x);
        if (flag) {
            s = s.substring(1);
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length >> 1; i++) {
            char c = chars[i];
            chars[i] = chars[chars.length - 1 - i];
            chars[chars.length - 1 - i] = c;
        }
        tem = Long.valueOf(String.copyValueOf(chars));
        if (flag) {
            if (tem - 1 > Integer.MAX_VALUE){
                return 0;
            }else return (int) -tem;
        }else {
            if (tem > Integer.MAX_VALUE){
                return 0;
            }else {
                return (int) tem;
            }
        }
    }
}
