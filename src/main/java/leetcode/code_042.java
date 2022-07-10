package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class code_042 {

    public int trap(int[] height) {
        int sum = 0;
        int max_left = 0;
        int[] max_right = new int[height.length];
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            max_left = Math.max(max_left, height[i - 1]);
            int min = Math.min(max_left, max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    // 1.动态规划
    public int trap1(int[] height) {
        int n = height.length;
        if(n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return ans;
    }

    //单调栈
    public int trap2(int[] height) {

        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]){
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }

                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.max(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;

            }
            stack.push(i);
        }

        return ans;
    }

    // 双指针
    public int trap3(int[] height) {

        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0,rightMax = 0;
        while(left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++ left;
            } else {
                ans += rightMax - height[right];
                -- right;
            }
        }

        return ans;
    }

    // 手写动态规划
    public int trap4(int[] height) {
        int res = 0;
        if (height == null || height.length == 0) return res;
        int n = height.length;


        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        for (int i = 0; i < n; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }

    //手写单调栈
    public int trap5(int[] height) {

        int res = 0;
        if (height == null || height.length == 0) return res;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;


        for (int i = 0; i < n; i++) {
            // 单调栈比的是高度
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {

                int top = stack.pop();
                if (stack.isEmpty()) break;

                int left = stack.peek();
                // 每个柱子占一个位置，所以需要减1
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                res += currWidth * currHeight;


            }
            // 栈中存放的是数组下标
            stack.push(i);
        }

        return res;
    }

    public static void main(String[] args) {

        code_042 code = new code_042();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int max = code.trap5(height);
        System.out.println(max);

    }


}
