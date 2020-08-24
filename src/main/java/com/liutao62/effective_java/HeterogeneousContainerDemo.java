package com.liutao62.effective_java;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liutao
 * @date Created in 2020/8/25 0:21
 * @description 使用类型安全的异构容器
 */
public class HeterogeneousContainerDemo {
    public static void main(String[] args) {
        Favorites favorites = new Favorites();
        favorites.put(String.class, "Java");
        favorites.put(Integer.class, 1);
        // 类型安全：请求 String 就不会返回 Integer
        String s = favorites.get(String.class);
        Integer integer = favorites.get(Integer.class);
        System.out.println(s);
        System.out.println(integer);
    }
}

class Favorites {
    // 异构容器：不像 map 的所有键都是不同类型的？？？人傻了。。。。。。。
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void put(Class<T> type, T instance) {
        if (type == null) {
            throw new RuntimeException("type is null");
        }
        favorites.put(type, instance);
    }

    public <T> T get(Class<T> type) {
        return (T) favorites.get(type);
    }
}
