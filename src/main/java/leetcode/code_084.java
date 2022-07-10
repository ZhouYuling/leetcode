package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class code_084 {

    static class Solution {
        public int largestRectangleArea(int[] heights) {
            int n = heights.length;
            int[] left = new int[n];
            int[] right = new int[n];

            Stack<Integer> mono_stack = new Stack<Integer>();
            for (int i = 0; i < n; ++i) {
                while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                    mono_stack.pop();
                }
                left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
                mono_stack.push(i);
            }

            mono_stack.clear();
            for (int i = n - 1; i >= 0; --i) {
                while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                    mono_stack.pop();
                }
                right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
                mono_stack.push(i);
            }

            int ans = 0;
            for (int i = 0; i < n; ++i) {
                ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
            }
            return ans;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] heights = {2,1,5,6,2,3};
        int i = solution.largestRectangleArea(heights);
        System.out.println(i);

    }

    //1.暴力解法
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            int left = i;
            int curHeight = heights[i];
            while (left > 0 && heights[left - 1] >= curHeight) {
                left --;
            }

            //找到右边最后1个大于等于heights[i]的索引
            int right = i;
            while (right < len - 1 && heights[right + 1] > curHeight)
                right ++;

            int width = right - left + 1;
            res = Math.max(res, width * curHeight);
        }

        return res;
    }

    public int largestRectangleArea1(int[] heights) {

        int len = heights.length;
        if (len == 0)
            return 0;
        if (len == 1)
            return heights[0];

        int res = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>(len);
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight)
                    stack.pollLast();

                int curWidth;
                if (stack.isEmpty())
                    curWidth = i;
                else
                    curWidth = i - stack.peekLast() - 1;

                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }

        while (!stack.isEmpty()) {
            int curHeight = heights[stack.pollLast()];
            while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight)
                stack.pollLast();
            int curWidth;
            if (stack.isEmpty())
                curWidth = len;
            else
                curWidth = len - stack.peekLast() - 1;
            res = Math.max(res, curHeight * curWidth);
        }
        return res;

    }

}
