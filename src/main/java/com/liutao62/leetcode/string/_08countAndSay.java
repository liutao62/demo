package com.liutao62.leetcode.string;

/**
 * @author liutao
 * @date Created in 2020/8/30 18:27
 * @description
 */
public class _08countAndSay {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        char[] chars = "1".toCharArray();
        int count = 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < n; i++) {
            char c = chars[0];
            count = 1;
            for (int j = 0; j < chars.length - 1; ) {
                if (c == chars[++j]) {
                    count++;
                } else {
                    builder.append(count).append(c);
                    c = chars[j];
                    count = 1;
                }
            }
            builder.append(count).append(c);
            if (i + 1 < n) {
                chars = builder.toString().toCharArray();
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }
}
