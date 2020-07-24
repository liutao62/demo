package com.liutao62.leetcode;

public class _38countAndSay {
    public static String countAndSay(int n) {
        if (n == 1) return "1";
        return say(countAndSay(n - 1));
    }

    private static String say(String str) {
        char num = str.charAt(0);
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == num){
                count++;
            }else {
                sb.append(count + "" + num);
                num = c;
                count = 1;
            }
        }
        sb.append(count + "" + num);
        return sb.toString();
    }
}
