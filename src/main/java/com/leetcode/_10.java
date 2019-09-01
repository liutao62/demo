package com.leetcode;

/**
 * @description 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
public class _10 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        int sLen = s.length(), pLen = p.length();
        boolean[][] matches = new boolean[pLen][sLen];
        int i = 0,i1 = 0;
        int count = 0;      //用于计算 * 匹配个数
        for (i = 0; i < sLen; i++) {
            for (i1 = i + count; i1 < pLen; i1++) {
                char schar = s.charAt(i), pchar = p.charAt(i1);
                if (schar == pchar || pchar == '.') {
                    matches[i1][i] = true;
                    break;
                } else {
                    //*为0
                    if (i1 < pLen - 1 && p.charAt(i1 + 1) == '*') {
                        i1++;
                    } else if (pchar == '*' && i1 > 0) {
                        //*为1 - n
                        if (schar == p.charAt(i1 - 1) || p.charAt(i1 - 1) == '.') {
                            matches[i1][i] = true;
                            count--;
                            break;
                        } else { //*结束
                            count++;
                            i--;
                            break;
                        }
                    } else matches[i1][i] = false;//不等
                }
            }
            if (i1 == pLen - 1) break; //匹配串到尾
        }
        //匹配串未完  如s：aaa p：a*aa   s：ab  p：.*c
        if (sLen == i && pLen > i1) {
            //正常匹配结束
            if (count == 0) return false;
            else {//带*匹配导致结束

            }
        }
        count = 0;
        for (i = 0; i < sLen; i++) {
            for (i1 = 0; i1 < pLen; i1++) {
                if (matches[i1][i]) {
                    count++;
                    break;
                }
            }
        }
        if (count == sLen) return true;
        return false;
    }
}
