package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class code_946 {

    class Solution {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            int N = pushed.length;
            Deque<Integer> stack = new LinkedList<>();
            int j = 0;
            for (int x:
                 pushed) {
                stack.push(x);
                while (!stack.isEmpty() && j < N && stack.peek() == popped[j]) {
                    stack.pop();
                    j ++;
                }
            }

            return j == N;


        }
    }

}
