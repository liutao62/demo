package com.leetcode;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class _03 {
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        LinkedHashSet<Character> list = new LinkedHashSet<>();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            if (list.contains(chars[i])) {
                result = result > list.size() ? result : list.size();
                Iterator<Character> iterator = list.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next() == chars[i]) {
                        iterator.remove();
                        break;
                    }
                    iterator.remove();
                }
            }
            list.add(chars[i]);
        }
        result = result > list.size() ? result : list.size();
        return result;
    }
}
