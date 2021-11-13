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

        com.liutao62.data_structure.queue.PriorityQueue myQueue = new com.liutao62.data_structure.queue.PriorityQueue((a, b) -> {
            return map.get(a) - map.get(b);
        });

        map.keySet().forEach(key -> {
            if (priorityQueue.size() < k) {
                priorityQueue.add(key);
                myQueue.add(key);
            } else if (map.get(key) > map.get(priorityQueue.peek())) {
                priorityQueue.poll();
                priorityQueue.add(key);

                myQueue.poll();
                myQueue.add(key);
            }
        });

        System.out.println("----------------priority");
        Arrays.stream(priorityQueue.stream().mapToInt(i -> i).toArray()).forEach(System.out::println);
        System.out.println("----------------my queue");
        Arrays.stream(myQueue.queue).forEach(System.out::println);

        // Arrays.stream(myQueue.stream().mapToInt(i -> (int) i).toArray()).forEach(System.out::println);

        return priorityQueue.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] ints = new _347topKFrequent().topKFrequent(new int[]{1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8}, 4);
        Arrays.stream(ints).forEach(System.out::println);
    }
}
