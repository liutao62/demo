package com.liutao62;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

        User user1 = new User();
        user1.id = "1111";
        User user2 = new User();
        user2.id = "1112";
        User user3 = new User();
        user3.id = "1113";
        User[] users = {user1, user2, user3};
        Map<String, User> map = Arrays.stream(users).collect(Collectors.toMap(User::getId, v -> v));
        System.out.println(map);
    }
}

class User {
    public String id;
    public String name;

    public String getId() {
        return id;
    }
}