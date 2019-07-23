package com.javasedemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaDemo {
    public static void main(String[] args) {
        //1.
        new Thread(
                () -> System.out.println("lambda ")
        ).start();
        //2.
        List<Integer> list = new ArrayList();
        for (int i = 0; i < 10; i++) list.add(i);
        list.forEach(n -> {
            if (n > 4) System.out.print(n);
        });
        list.forEach(System.out::println);
        list.forEach(n -> System.out.println(n));

        //3.
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> ((String) str).startsWith("J"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

    }


    public static void filter(List<String> names, Predicate condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }
}
