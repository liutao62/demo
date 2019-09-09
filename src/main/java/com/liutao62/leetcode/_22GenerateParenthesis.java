package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _22GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generate(list, "", 0, 0, n);
        return list;
    }

    private void generate(List<String> list, String str, int count1, int count2, int n) {
        if (count1 > n || count2 > n) return;
        if (count1 == n && count2 == n && isVaild(str)) list.add(str);

        generate(list, str + "(", count1 + 1, count2, n);
        generate(list, str + ")", count1, count2 + 1, n);

    }

    private boolean isVaild(String str) {
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') list.push(str.charAt(i));
            else {
                Character pop = list.poll();
                if (pop == null || '(' != pop) return false;
            }
        }
        return true;
    }
}
