package com.liutao62.effective_java;

import java.util.Arrays;

/**
 * @author liutao
 * @date Created in 2020/8/16 22:38
 * @description
 */
public class HashCodeDemo {
    private boolean bool;
    private byte b;
    private char c;
    private short s;
    private int i;
    private long l;
    private float f;
    private double d;

    private int[] arr;

    // 如果计算散列码的开销比较大，应该考虑将散列码缓存在对象内部
    private volatile int hashCode;

    // 不需要参与 equals 或者生成 hashCode 的非必要域
    private int other;
    private Object obj;

    /**
     * @return
     * @description 需要单元测试验证自己的推断
     * <p>
     * 不要试图从散列码计算中排除掉一个对象的关键部分来提高性能，可能会导致散列表慢到无法使用
     * <p>
     * 使用 31 的原因：1、31 是奇素数，偶素数并且乘法溢出的话信息就会丢失
     * 2、可以使用移位和剑法来代替乘法，可以得到更好的性能：31 * i == (i << 5) - i ，现代的 VM 可以自动完成这种优化
     */
    @Override
    public int hashCode() {
        int result = this.hashCode;
        if (result == 0) {
            result = 1;
            // boolean 类型
            result = result * 31 + (bool ? 1 : 0);

            // byte、char、short、int类型
            result = result * 31 + b;
            result = result * 31 + c;
            result = result * 31 + s;
            result = result * 31 + i;

            // long
            result = result * 31 + (int) (l ^ (l >>> 32));

            // float
            result = result * 31 + Float.floatToIntBits(f);
            // double
            long d2l = Double.doubleToLongBits(d);
            result = result * 31 + (int) (d2l ^ (d2l >>> 32));

            // array(如果数组域中的每个元素都很重要)
            result = result * 31 + Arrays.hashCode(arr);
            this.hashCode = result;
        }
        return result;
    }
}