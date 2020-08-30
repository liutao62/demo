package com.liutao62.effective_java;

import java.util.*;

/**
 * @author liutao
 * @date Created in 2020/8/30 23:48
 * @description
 */
public class OverloadDemo {
    private static String classify(Set<?> set) {
        return "Set";
    }

    private static String classify(List<?> list) {
        return "List";
    }

    private static String classify(Collection<?> collection) {
        return "Unknown Collection";
    }
/*
    正确的相同参数个数的重载姿势
    public static String classify(Collection<?> collection) {
        return collection instanceof Set ? "Set" :
                collection instanceof List ? "List" : "Unknown Collection";
    }
*/

    public static void main(String[] args) {
        // 重载 demo1
        // 一般定义都使用接口定义类型，此处定义 Collection 只是接口大了些，显示效果
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<String>(),
                new HashMap<String, String>().values()
        };
        for (Collection<?> collection : collections) {
            // 编译时期静态选择重载方法
            System.out.println(classify(collection));
        }
        // 覆盖 demo
        Wine[] wines = {
                new Wine(),
                new SparklingWine(),
                new Champagne()
        };

        for (Wine wine : wines) {
            // 运行时期动态选择覆盖方法
            System.out.println(wine.name());
        }

        // 重载 demo2
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            set.add(i);
            list.add(i);
        }
        for (int i = 0; i < 3; i++) {
            set.remove(i);
            // 此处移除 i 是静态调用了 remove(int i) 删除指定索引位置的元素抛出越界异常
            // 展示了重载的混乱行为
            // 自动装箱和泛型成为 Java 语言的一部分后，谨慎重载显得更加重要了
//            list.remove(i);
            list.remove(Integer.valueOf(i));
        }
        System.out.println(set);
        System.out.println(list);
    }
}

class Wine {
    String name() {
        return "wine";
    }
}

class SparklingWine extends Wine {
    @Override
    String name() {
        return "SparklingWine";
    }
}

class Champagne extends Wine {
    @Override
    String name() {
        return "Champagne";
    }
}
