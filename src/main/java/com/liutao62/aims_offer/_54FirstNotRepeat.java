package com.liutao62.aims_offer;

/**
 * @author liutao
 * @date Created in 2020/12/14 16:35
 * @description 题目描述
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 返回值描述:
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 */
public class _54FirstNotRepeat {

    private java.util.List<Character> input = new java.util.ArrayList<>();

    private java.util.List<Character> repeat = new java.util.ArrayList<>();

    private char firstNotRepeat = '#';

    //Insert one char from stringstream
    public void Insert(char ch) {
        if (input.contains(ch)) {
            if (!repeat.contains(ch)) {
                repeat.add(ch);
            }
        } else {
            input.add(ch);
        }
        firstNotRepeat = getFirstNotRepeat();
    }

    private char getFirstNotRepeat() {
        for (Character ch : input) {
            if (!repeat.contains(ch)) {
                return ch;
            }
        }
        return '#';
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        return firstNotRepeat;
    }
}
