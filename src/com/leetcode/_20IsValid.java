package com.leetcode;

import java.util.LinkedList;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _20IsValid {
    public boolean isValid(String s) {

        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (valid(list.peek(), s.charAt(i))) {
                list.pop();
            } else list.push(s.charAt(i));
        }
        if (list.size() == 0) return true;
        return false;
    }

    private boolean valid(Character s, Character s1) {
        if (s == null || s1 == null) return false;
        if ('(' == s && ')' == s1) return true;
        else if ('[' == s && ']' == s1) return true;
        else if ('{' == s && '}' == s1) return true;
        return false;
    }
}