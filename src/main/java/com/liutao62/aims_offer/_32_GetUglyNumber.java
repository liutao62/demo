package com.liutao62.aims_offer;

/**
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class _32_GetUglyNumber {
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) return 0;
        int[] uglyNums = new int[index];
        uglyNums[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < index; ++i) {
            int min = min(uglyNums[p2] * 2, uglyNums[p3] * 3, uglyNums[p5] * 5);
            uglyNums[i] = min;
            if (min == uglyNums[p2] * 2) ++p2;
            if (min == uglyNums[p3] * 3) ++p3;
            if (min == uglyNums[p5] * 5) ++p5;
        }
        return uglyNums[index - 1];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
