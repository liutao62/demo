package com.liutao62.leetcode;

import java.util.PriorityQueue;

public class _215findKthLargest {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int val : nums){
            pq.add(val);
            if(pq.size() > k) pq.poll();
        }
        return pq.peek();
    }
}
