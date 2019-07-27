package com.aims_offer;

/*
时间限制：1秒 空间限制：32768K 热度指数：772290
本题知识点： 字符串

题目描述
    请实现一个函数，将一个字符串中的每个空格替换成“%20”。
    例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。

*/
public class _02_ReplaceSpace {
    public String replaceSpace(StringBuffer str) {
        int lastIndexOf = str.lastIndexOf(" ");
        while (lastIndexOf >= 0) {
            str.deleteCharAt(lastIndexOf);
            str.insert(lastIndexOf, "%20");
            lastIndexOf = str.lastIndexOf(" ");
        }
        return str.toString();
    }
}
