package com.javasedemo;

public class CharNumbersCount {
    int english = 0,
            space = 0,
            num = 0,
            other = 0;

    public static void main(String[] args) {
        CharNumbersCount count = new CharNumbersCount();
        count.getNums("qwer,234**&  2ea");
        System.out.println(count.english + "  " + count.space + "  " + count.num + "  " + count.other);
    }
    private void getNums(String str) {
        char[] chars = new char[str.length()];
        str.getChars(0, chars.length, chars, 0);
        for (char c : chars) {
            if ( ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')) english++;
            else if (c == ' ') space++;
            else if (48 <= c && c <= 57) num++;
            else other++;

            System.out.print(c);
        }
    }
}
