package com.liutao62.effective_java;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author liutao
 * @date Created in 2020/8/22 0:12
 * @description
 */
public class StrategyDemo {
}

/**
 * @description 用法思考：比如假勤的不同规则，有不同的算法细节，通过定义策略接口，不同的规则内部静态类实现，提供各自的计算策略
 */
class Host {

    // 共有静态域或者静态工厂方法
    public static final Comparator<String> STRING_LENGTH_COMPARATOR = new StrLenComparator();

    /**
     * @description 因为策略接口被用作所有具体策略实例的类型，所以我们并不需要为了导出具体的策略，把具体测率类做成共有的。
     * 做成静态成员类而不是匿名类方便具体的策略类实现其他接口
     */
    private static class StrLenComparator implements Comparator<String>, Serializable {

        @Override
        public int compare(String s1, String s2) {
            if (isEmpty(s1) && isEmpty(s2)) {
                return 0;
            } else if (isEmpty(s1)) {
                return -s2.length();
            } else if (isEmpty(s2)) {
                return s1.length();
            }
            return s1.length() - s2.length();
        }

        private boolean isEmpty(String str) {
            return str == null || "".equals(str);
        }
    }

}
