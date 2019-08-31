package com.leetcode;

/**
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _29divide {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        boolean flag = (dividend | divisor) > 0 || (dividend ^ divisor) >= 0;
        long d = Math.abs((long) dividend);
        long r = Math.abs((long) divisor);
        if (flag && d < r) return 0;
        long count = 1 ,count1 = 0;
        while (d >= r << 1) {
            d >>= 1;
            count <<= 1;
        }
        while (d > r) {
            d -= r;
            count1++;
        }
        count += count1;
        return flag ? (int) (count > Integer.MAX_VALUE ? Integer.MAX_VALUE : count) :
                count > Integer.MAX_VALUE ? Integer.MIN_VALUE : (int) -count;
    }
}
