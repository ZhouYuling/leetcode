package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class code_735 {

    class Solution {
        public int[] asteroidCollision(int[] asteroids) {
            Deque<Integer> stack = new LinkedList<>();
            if (asteroids == null || asteroids.length == 0) return new int[0];
            for (int asteroid : asteroids) {
                boolean alive = true;
                while (!stack.isEmpty() && alive && asteroid < 0 && stack.peek() > 0) {
                    alive = stack.peek() < -asteroid;
                    if (stack.peek() <= -asteroid) stack.poll();
                }
                if (alive) stack.push(asteroid);
            }
            int[] res = new int[stack.size()];
            for (int i = stack.size() - 1; i >= 0; i--) {
                res[i] = stack.pop();
            }

            return res;
        }
    }

}
