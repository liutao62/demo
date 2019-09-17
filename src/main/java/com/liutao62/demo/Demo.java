package com.liutao62.demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int p = -1, num = 0;
        ArrayList<Integer> in = new ArrayList<>();
        LinkedList<Integer> out = new LinkedList<>();
        while (input.hasNext()) {
            String temp = null;
            if ((temp = input.next()).length() > 1) {
                String[] split = temp.split(",");
                if (p == -1) {
                    p = Integer.parseInt(split[0]);
                } else {
                }
            }
            System.out.println();
            try {
                while (true) {
                    int v = input.nextInt();
                    out.add(v);
                }
            } catch (Exception e) {
            }
            while (!out.isEmpty()) {
                Integer peek = out.peek();
                if (peek != null) {
                    out.pop();
                    if (p >= peek) {
                        in.add(peek);
                        p -= peek;
                        num++;
                    } else if (num > 0) {
                        int max = findMax(in, peek);
                        if (max != 0) {
                            p += max;
                            num--;
                        }
                    }
                }
            }
            System.out.println(num);
        }
    }

    public static int findMax(ArrayList<Integer> list, int peek) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > peek) {
                max = list.get(i);
                index = i;
            }
        }
        if (index != -1)
            list.remove(index);
        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
