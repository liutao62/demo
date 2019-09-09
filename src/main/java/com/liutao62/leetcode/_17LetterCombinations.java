package com.liutao62.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class _17LetterCombinations {

    private Map<Character, String[]> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        LinkedList<String> list = new LinkedList<>();
        if (digits == null || digits.length() == 0) return list;
        initializeMap();
        // 用于下面 while 循环避免空指针
        list.add("");
        for (int i = 0; i < digits.length(); i++) {
            char c = digits.charAt(i);
            String[] strings = map.get(c);

            // 根据 list 中每个元素的长度来决定是否重新组合并移除
            while (list.peek().length() == i) {
                String s1 = list.remove();
                for (String s : strings) {
                    list.add(s1 + s);
                }
            }
        }
        return list;
    }

    private void initializeMap() {
        map.put('2', new String[]{"a", "b", "c"});
        map.put('3', new String[]{"d", "e", "f"});
        map.put('4', new String[]{"g", "h", "i"});
        map.put('5', new String[]{"j", "k", "l"});
        map.put('6', new String[]{"m", "n", "o"});
        map.put('7', new String[]{"p", "q", "r", "s"});
        map.put('8', new String[]{"t", "u", "v"});
        map.put('9', new String[]{"w", "x", "y", "z"});
    }
}
