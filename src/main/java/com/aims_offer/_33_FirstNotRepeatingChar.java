package com.aims_offer;

/**
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 */
public class _33_FirstNotRepeatingChar {
    public int FirstNotRepeatingChar(String str) {
        char[] chars = new char[str.length()];
        str.getChars(0, str.length(), chars, 0);
        java.util.Set<Character> set = new java.util.LinkedHashSet<>();
        java.util.Map<Character, Integer> map = new java.util.HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            set.add(chars[i]);
            if (map.get(chars[i]) == null) map.put(chars[i], 1);
            else map.put(chars[i], map.get(chars[i]) + 1);
        }
        java.util.Iterator<Character> iterator = set.iterator();
        while (iterator.hasNext()) {
            Character next = iterator.next();
            if (map.get(next) == 1) {
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == next) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
