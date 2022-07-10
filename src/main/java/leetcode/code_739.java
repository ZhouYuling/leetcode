package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class code_739 {

    public int[] dailyTemperatures1(int[] temperatures) {

        int length = temperatures.length;
        int[] ans = new int[length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = length - 1; i >= 0; i--) {
            int warmerIndex = Integer.MAX_VALUE;
            for (int t = temperatures[i] + 1; t <=100; ++t) {
                if (next[t] < warmerIndex ) {
                    warmerIndex  = next[t];
                }
            }
            if (warmerIndex  < Integer.MAX_VALUE)
                ans[i] = warmerIndex  - i;
            next[temperatures[i]] = i;
        }

        return ans;
    }


    public static void main(String[] args) {

        int[] temperatures = {30, 40, 50, 60};
        code_739 code = new code_739();
        int[] ints = code.dailyTemperatures1(temperatures);
        for (int i:ints) {
            System.out.println(i);
        }

    }


    public int[] dailyTemperatures2(int[] temperatures) {

        int length = temperatures.length;
        int[] ans = new int[length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]){
                int preIndex = stack.pop();
                ans[preIndex] = i - preIndex;
            }
            stack.push(i);
        }

        return ans;
    }

}
