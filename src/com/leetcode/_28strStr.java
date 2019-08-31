package com.leetcode;

/**
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _28strStr {
    public int strStr(String haystack, String needle) {
        if (haystack == null || "".equals(haystack)) return -1;
        if (needle == null || needle.length() == 0) return 0;
        return this.kmp(haystack, needle);
    }

    private int kmp(String text, String target) {
        if (text == null || target == null || "".equals(text)
                || "".equals(target))
            return 0;
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

    private int[] getNext(String target) {
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
