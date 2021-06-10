package com.liutao62.leetcode.scope;

/**
 * @author liutao
 * @date Created in 2021/4/11 17:00
 * @description N 皇后
 */
public class Queens {

    public static void main(String[] args) {
        int process = process(14);
        System.out.println(process);

        int process2 = process2(14);
        System.out.println(process2);
    }

    /**
     * @param n
     * @return
     * @description 超过 32 位可以尝试用 long，不知道要跑多久
     */
    public static int process(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 完成态 n == 32 finish = 1111 1111 1111 1111 1111 1111 1111 1111
        // 负数二进制表示  二进制原码的反码 + 1
        // 如 n == 8 finish = 0..0 1111 1111
        int finish = n == 32 ? -1 : (1 << n) - 1;
        return processHelper(finish, 0, 0, 0);
    }

    /**
     * @param finish        完成态二进制表示
     * @param colLimit      列限制二进制表示
     * @param leftDiaLimit  左斜线限制二进制表示
     * @param rightDiaLimit 右斜线限制二进制表示
     * @return
     * @description 通过位运算提升常数项效率，时间复杂度O(N^2)
     */
    private static int processHelper(int finish, int colLimit, int leftDiaLimit, int rightDiaLimit) {
        // 如果列限制等于完成态。则返回 1 ，说明当前摆法有效
        // 说明列上已经摆了足够的皇后了
        if (colLimit == finish) {
            return 1;
        }
        // 将限制位上的 1 转换为 0，把可尝试位 0 转换为 1.并截干扰项（finish 前面都为 0 ，截取干扰项）
        int pos = finish & (~(colLimit | leftDiaLimit | rightDiaLimit));

        int count = 0;
        int mostRightOne = 0;
        while (pos != 0) {
            // 将可尝试的最右侧的 1 拿出来
            mostRightOne = pos & (~pos + 1);
            // 把尝试位去掉
            pos = pos - mostRightOne;
            count += processHelper(finish,
                    // 列限制位就是之前的列限制加上现在的列限制
                    colLimit | mostRightOne,
                    // 左斜限制，之前的左斜限制加上现在的列限制，然后左移一位
                    (leftDiaLimit | mostRightOne) << 1,
                    // 右斜限制，之前的右斜限制加上现在的列限制，然后右移一位
                    (rightDiaLimit | mostRightOne) >>> 1);
        }
        return count;
    }

    public static int process2(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return processHelper2(record, 0, n);
    }

    private static int processHelper2(int[] record, int row, int n) {
        if (n == row) {
            return 1;
        }
        int count = 0;
        for (int col = 0; col < record.length; col++) {
            // 如果可以拜访，则进行下一行，并设置皇后当前行摆放在哪一列
            if (isValid(record, row, col)) {
                record[row] = col;
                count += processHelper2(record, row + 1, n);
            }
        }
        return count;
    }

    private static boolean isValid(int[] record, int row, int col) {
        // 验证当前行 row 之前的
        for (int i = 0; i < row; i++) {
            // col == record[i] 列冲突
            // Math.abs(i - row) == Math.abs(record[i] - col) 左斜或者右斜冲突
            if (col == record[i] || Math.abs(i - row) == Math.abs(record[i] - col)) {
                return false;
            }
        }
        return true;
    }
}

