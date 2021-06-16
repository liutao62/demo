package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2021/6/16 20:23
 * @description 如果正整数可以被 A 或 B 整除，那么它是神奇的。
 * 返回第 N 个神奇数字。由于答案可能非常大，返回它模 10^9 + 7 的结果。
 */
public class _878nthMagicalNumber {

    public int nthMagicalNumber(int n, int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        int min = Math.min(a, b);
        if (a % b == 0 || b % a == 0) {
            return (int) ((Long.valueOf(min) * n) % ((int) Math.pow(10, 9) + 7));
        }

        int count = 0;
        List<Long> list = new ArrayList<>();

        int multiple = getMinCommonMultiple(a, b);

        for (int i = min; i <= multiple; i++) {
            if (i % a == 0 || i % b == 0) {
                list.add(Long.valueOf(i));
                ++count;
            }
            if (count == n) {
                return i;
            }
        }
        count = n / list.size();
        int mod = n % list.size();

        return (int) (((mod != 0 ? list.get(mod - 1) : 0) + count * list.get(list.size() - 1)) % ((int) Math.pow(10, 9) + 7));
    }

    public int getMinCommonMultiple(int a, int b) {
        int num = a * b;
        int i = Math.min(a, b) - 1;
        while (i > 1 && num > i) {
            if (a % i == 0 && b % i == 0) {
                num /= i;
                a /= i;
                b /= i;
            }
            i--;
        }

        return num;
    }

    public static void main(String[] args) {
        _878nthMagicalNumber nthMagicalNumber = new _878nthMagicalNumber();
        System.out.println(nthMagicalNumber.nthMagicalNumber(1000000000, 40000, 40000));
        System.out.println(nthMagicalNumber.nthMagicalNumber(5, 7, 5));
        System.out.println(nthMagicalNumber.nthMagicalNumber(3, 6, 4));
        System.out.println(nthMagicalNumber.nthMagicalNumber(422, 656, 88));
    }
}
