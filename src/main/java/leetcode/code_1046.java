package leetcode;

import java.util.PriorityQueue;

public class code_1046 {

    class Solution {
        public int lastStoneWeight(int[] stones) {

            PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
            for (int i = 0; i < stones.length; i++) {
                queue.offer(stones[i]);
            }
            while (queue.size() > 1) {
                int big = queue.poll();
                int small = queue.poll();
                if (big == small) continue;
                queue.offer(big - small);
            }
            return queue.isEmpty() ? 0 : queue.poll();
        }
    }

}
