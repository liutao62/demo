package com.leetcode;

import org.junit.Test;

import java.util.List;

public class _17LetterCombinationsTest {
    private _17LetterCombinations test = new _17LetterCombinations();

    @Test
    public void lettercombinations() {
        List<String> list = test.letterCombinations("23");
        System.out.println(list);
    }
}