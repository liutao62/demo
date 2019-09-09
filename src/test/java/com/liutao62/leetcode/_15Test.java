package com.liutao62.leetcode;

import org.junit.Test;

import java.util.List;

public class _15Test {

    private _15 test = new _15();
    private int[] nums = {0, 3, 0, 1, 1, -1, -5, -5, 3, -3, -3, 0};

    @Test
    public void threeSum() {
        List<List<Integer>> lists = test.threeSum(nums);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }

        /*List<Integer> list1 = new ArrayList<>(4);
        List<Integer> list2 = new ArrayList<>(4);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        List<Integer> clone = (ArrayList<Integer>) ((ArrayList<Integer>) list1).clone();

        System.out.println(list1.removeAll(list2));

        System.out.println(list1);
        System.out.println(list2);
        System.out.println(clone);*/
    }
}