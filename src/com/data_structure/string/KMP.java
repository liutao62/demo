package com.data_structure.string;

public class KMP {
    public int kmp(String text, String target) {
        if (text == null || target == null || "".equals(text)
                || "".equals(target))
            return -1;
        char[] targetChars = target.toCharArray();
        char[] textChars = text.toCharArray();
        int[] next = getNext(target);
        int textIndex = 0, targetIndex = 0;
        while (targetIndex < targetChars.length && textIndex < textChars.length) {
            if (targetIndex == -1 || targetChars[targetIndex] == textChars[textIndex]) {
                ++targetIndex;
                ++textIndex;
            } else targetIndex = next[targetIndex];
        }
        if (targetIndex == targetChars.length) return textIndex - targetIndex;
        return -1;
    }

    public int[] getNext(String target) {
        char[] p = target.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                if (p[++j] == p[++k]) { // 当两个字符相等时要跳过
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
