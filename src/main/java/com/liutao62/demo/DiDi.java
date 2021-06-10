package com.liutao62.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2021/6/10 13:43
 * @description 来自朋友滴滴的面试题
 *              100 张牌。初始朝上，牌面 1 - 100，能被翻牌次数 count 整除，则将牌面反制。问 100 次后，牌面向下的牌
 */
public class DiDi {

    /**
     * @param n
     * @return
     * @descripion
     */
    public int[] solution(int n) {
        if (n < 0) {
            return new int[0];
        }
        int[] arr = new int[n];

        int i, j;
        for (i = 1; i <= n; i++) {
            for (j = i; j <= n; j++) {
                if (j % i == 0) {
                    // 反转，0为朝上，1朝下
                    arr[j - 1] = arr[j - 1] == 0 ? 1 : 0;
                }
            }
        }
        List<Integer> result = new ArrayList<>();

        for (int index = 0; index < arr.length; index++) {
            if (arr[index] == 1) {
                result.add(index + 1);
            }
        }
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static void main(String[] args) {
        int[] result = new DiDi().solution(100);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

}
