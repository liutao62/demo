package com.liutao62.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liutao
 * @date Created in 2021/8/15 23:53
 * @description
 */
public class _49groupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groupMap = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            // String s = chars.toString();
            String s = new String(chars);
            List<String> list = groupMap.getOrDefault(s, new ArrayList<>());
            if (list.size() == 0) {
                groupMap.put(s, list);
            }
            list.add(str);
        }
        return groupMap.values().stream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<List<String>> lists = new _49groupAnagrams().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(lists);
    }
}
