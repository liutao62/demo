package com.liutao62;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liutao
 * @date Created in 2020/8/27 16:23
 * @description
 */
public class Demo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.stream(new Integer[]{1, 2, 3, 4}).collect(Collectors.toList());
        List<Integer> list1 = Arrays.stream(new Integer[]{}).collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7}).collect(Collectors.toList());

        System.out.println(list1.stream().filter(k -> list.contains(k)).collect(Collectors.toList()));
        System.out.println(list2.stream().filter(k -> list.contains(k)).collect(Collectors.toList()));
    }
}
