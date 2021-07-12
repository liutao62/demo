package com.liutao62.leetcode;

import java.util.*;

/**
 * @author liutao
 * @date Created in 2021/7/12 22:44
 * @description
 */
public class _347topKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length, 1);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> {
            return map.get(a) - map.get(b);
        });

        map.entrySet().forEach(entry -> {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (priorityQueue.size() < k) {
                priorityQueue.add(key);
            } else if (map.get(key) > map.get(priorityQueue.peek())) {
                priorityQueue.poll();
                priorityQueue.add(key);
            }
        });
        return priorityQueue.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] ints = new _347topKFrequent().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        Arrays.stream(ints).forEach(System.out::println);
    }
}
