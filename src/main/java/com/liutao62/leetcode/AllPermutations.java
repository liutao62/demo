package com.liutao62.leetcode;

import java.util.*;

/**
 * @author liutao
 * @date Created in 2021/4/7 22:44
 * @description 从左往右的尝试模型：获取字符的全排列
 */
public class AllPermutations {

    public static void main(String[] args) {
        String str = "abc";
        List<String> permutations = new ArrayList<>();
        char[] chars = str.toCharArray();
        process(0, chars, permutations);
        System.out.println(permutations);

        String noRepeat = "aaababb";
        char[] chars1 = noRepeat.toCharArray();
        Set<String> noRepeatBySet = new HashSet<>();
        processNoRepeat1(0, chars1, noRepeatBySet);
        System.out.println(noRepeatBySet);

        List<String> noRepeatByVisit = new ArrayList<>();
        processNoRepeat2(0, noRepeat.toCharArray(), noRepeatByVisit);
        System.out.println(noRepeatByVisit);

        noRepeatByVisit.stream().sorted().forEach(System.out::print);
        System.out.println();
        noRepeatBySet.stream().sorted().forEach(System.out::print);
    }

    /**
     * @param begin
     * @param chars
     * @param permutations
     * @description 0 ~ begin - 1 的位置已经决定好了不再修改。如果 begin == chars.length 说明当前 chars 的顺序就是当前的排列结果
     */
    private static void process(int begin, char[] chars, List<String> permutations) {
        if (begin == chars.length) {
            permutations.add(String.valueOf(chars));
            return;
        }
        // 第一次：如 abc 所有字符都可以到第一个位置。
        for (int i = begin; i < chars.length; i++) {
            swap(begin, i, chars);
            // 确定了第一个的位置之后。当前的排列第一个位置就不再修改
            // 这里确定的是 begin 位置。for 循环给每一个后面的字符到达当前位置的机会，所以继续计算得从 begin + 1 开始
            process(begin + 1, chars, permutations);
            swap(begin, i, chars);
        }
    }

    // set去重
    private static void processNoRepeat1(int begin, char[] chars, Set<String> permutations) {
        if (begin == chars.length) {
            permutations.add(String.valueOf(chars));
            return;
        }
        for (int i = begin; i < chars.length; i++) {
            swap(begin, i, chars);
            processNoRepeat1(begin + 1, chars, permutations);
            swap(begin, i, chars);
        }
    }

    // 分支限界法。空间换时间
    private static void processNoRepeat2(int begin, char[] chars, List<String> permutations) {
        if (begin == chars.length) {
            permutations.add(String.valueOf(chars));
            return;
        }
        // 每一个 begin 的位置统计是否访问过都有一个单独的 map。所以可以直接使用判断
        Map<Character, Boolean> visitMap = new HashMap<>();
        for (int i = begin; i < chars.length; i++) {
            // 如果当前位置没有相同元素访问过。则可以交换。
            if (!Boolean.TRUE.equals(visitMap.get(chars[i]))) {
                visitMap.put(chars[i], true);
                swap(begin, i, chars);
                processNoRepeat2(begin + 1, chars, permutations);
                swap(begin, i, chars);
            }
        }
    }

    private static void swap(int i, int j, char[] chars) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
