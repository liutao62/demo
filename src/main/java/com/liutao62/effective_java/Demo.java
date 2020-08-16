package com.liutao62.effective_java;

public class Demo {
    public static void main(String[] args) {

        // 自动装箱性能测试
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("计算 Long sum = " + sum + " 总用时： " + (end - start));

        start = System.currentTimeMillis();
        long sum1 = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum1 += i;
        }
        end = System.currentTimeMillis();
        System.out.println("计算 long sum = " + sum + " 总用时： " + (end - start));


        // VM 对乘法优化测试
        start = System.currentTimeMillis();
        int product = 1;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            product = (product << 5) - product;
        }
        end = System.currentTimeMillis();
        System.out.println("计算 product <<= " + product + " spend time : " + (end - start));

        product = 1;
        start = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            product = product * 31;
        }
        end = System.currentTimeMillis();
        System.out.println("计算 product *= " + product + " spend time : " + (end - start));

        // 偶素数并且乘法溢出的话信息就会丢失（未知测试方法。看不到效果）
        int hashCode1 = 1, hashCode2 = 1;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            hashCode1 = hashCode1 * 2 + i;
            hashCode2 = hashCode2 * 31 + i;
            verify(hashCode1, hashCode2, i);
        }
    }

    private static void verify(int hash1, int hash2, int i) {
        for (int i1 = i; i1 > 0; i1--) {
            hash1 -= i1;
            hash1 /= 2;
            hash2 -= i1;
            hash2 /= 31;
        }
        assert hash1 == 1 && hash2 == 1;
    }
}
