package com.liutao62.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 优惠券问题（题目描述有问题。默认优惠券减100）
 * 使用System.in System.out
 * 写一个类带有main方法
 * 输入：优惠券使用条件（满减价格） 商品数量n 商品价格1 ... 商品价格n
 * 输出：最优惠价格(不能使用优惠券返回-1)
 * <p>
 * 如输入：1000 4 555 450 480 610 （满1000元才能使用优惠券，从四个商品中挑选并输出最低价格）
 * 输出： 905
 */
public class RenRen {
    /**
     * 不考虑输入错误/不符等情况
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //获得输入并根据空格拆分得子字符串
        String next = input.nextLine();
        String[] s = next.split(" ");

        Integer[] integers = new Integer[s.length];
        // 获得所有输入数值
        for (int i = 0; i < s.length; i++) {
            integers[i] = Integer.parseInt(s[i]);
        }

        // 满足条件
        int condition = integers[0];
        // 商品数量
        int num = integers[1];
        if (num == 1) {
            Integer price = integers[2];
            if (price > condition) {
                System.out.print(price - 100);
            } else {
                System.out.println(-1);
            }
            return;
        }
        Arrays.sort(integers, 2, integers.length - 1);

        for (Integer integer : integers) {
            System.out.print(integer + " ");
        }

        //用于存放所有满足条件商品价格组合
        List<Integer> list = new ArrayList<>();

        getOpt(0, 1000, list, integers, 0);

        System.out.println(list);

    }

    public static void getOpt(int total, int condition, List<Integer> list, Integer[] src, int index) {
        if (index < src.length - 1) {
            for (int i = index; i < src.length; i++) {
                int count = src[index];
                total += count;
                if (total > condition) {
                    list.add(total);
                } else if (total < condition) {
                    getOpt(count, condition, list, src, index + 1);
                } else {
                    list.add(total);
                    return;
                }
            }

        }
    }
}
