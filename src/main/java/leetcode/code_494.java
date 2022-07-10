package leetcode;

public class code_494 {

    public static class Solution {
        public int findTargetSumWays(int[] nums, int S) {
            int[] dp = new int[2001];
            dp[nums[0] + 1000] = 1;
            dp[-nums[0] + 1000] += 1;
            for (int i = 1; i < nums.length; i++) {
                int[] next = new int[2001];
                for (int sum = -1000; sum <= 1000; sum++) {
                    if (dp[sum + 1000] > 0) {
                        next[sum + nums[i] + 1000] += dp[sum + 1000];
                        next[sum - nums[i] + 1000] += dp[sum + 1000];
                    }
                }
                dp = next;
            }
            return S > 1000 ? 0 : dp[S + 1000];
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        int targetSumWays = solution.findTargetSumWays(nums, S);
        System.out.println(targetSumWays);

    }


    int count = 0;

    //回溯
    public int findTargetSumWays1(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            //满足条件
            if (sum == target) {
                count++;
            }
        } else {
            //方式一: 前面是+号
            backtrack(nums, target, index + 1, sum + nums[index]);
            //方式二: 前面是-号
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }

    //动态规划
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            //neg的边界似乎有点大，但是对结果没有影响
            for (int j = 0; j <= neg; j++) {
                //浅爷的写法
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }


}
