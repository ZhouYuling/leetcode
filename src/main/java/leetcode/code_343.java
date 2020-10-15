package leetcode;

public class code_343 {

    static class Solution {
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                int curMax = 0;
                for (int j = 1; j < i; j++) {
                    curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
                }
                dp[i] = curMax;
            }
            return dp[n];
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int i = solution.integerBreak(10);
        System.out.println(i);

    }

}
