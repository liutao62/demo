package com.liutao62.effective_java;

import java.util.HashMap;

/**
 * @author liutao
 * @date Created in 2020/8/11 16:13
 * @description
 */
public class StaticFactoryDemo {
    public static void main(String[] args) {
        HashMap<String, Object> objectObjectHashMap = HashMapFactory.newInstance();
    }
}

class HashMapFactory<K, V> {
    public static <K, V> HashMap<K, V> newInstance() {
        return new HashMap<K, V>();
    }
}