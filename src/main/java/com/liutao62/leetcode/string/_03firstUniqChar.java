package com.liutao62.leetcode.string;

import java.util.LinkedHashMap;
import java.util.Map;

public class _03firstUniqChar {    public int firstUniqChar(String s) {
    if (s == null || s.length() == 0) return -1;
    Map<Character, Integer> map = new LinkedHashMap<>();
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (map.get(c) == null) {
            map.put(c, 1);
        } else {
            map.put(c, map.get(c) + 1);
        }
    }

    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
        if (entry.getValue() == 1) {
            return s.indexOf(entry.getKey());
        }
    }
    return -1;
}

}
