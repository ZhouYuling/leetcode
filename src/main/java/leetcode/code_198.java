package leetcode;

public class code_198 {

    //动态规划
    class Solution {
        public int rob(int[] nums) {
            //边界值
            if (nums == null || nums.length == 0) {
                return 0;
            }
            //边界值，只有一个房屋
            int length = nums.length;
            if (length == 1) {
                return nums[0];
            }
            //定义动态规划状态数组
            int[] dp = new int[length];
            //第一个房屋能获得最高金额
            dp[0] = nums[0];
            //第二个房屋能获得最高金额
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < length; i++) {
                //状态转移方程，当前房间可以选择偷，也可以不选择偷，取金额最大值
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            return dp[length - 1];
        }
    }

    // 滚动数组降低空间复杂度
    class Solution2 {
        public int rob(int[] nums) {
            //边界
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int length = nums.length;
            //只有一个房子可以偷
            if (length == 1) {
                return nums[0];
            }

            //使用first和second接受状态值，另外使用temp临时变量
            int first = nums[0];
            int second = Math.max(nums[0], nums[1]);
            for (int i = 2; i < length; i++) {
                int temp = second;
                second = Math.max(first + nums[i], second);
                first = temp;
            }
            return second;
        }
    }

    //手写动态规划
    class Solution3 {
        // dp[i] 当前偷窃的最大金额
        // dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        // dp[0] = nums[0]
        // dp[1] = Math.max(nums[0], nums[1])
        public int rob(int[] nums) {

            if (nums == null || nums.length == 0) return 0;

            int n = nums.length;
            if (n == 1) return nums[0];
            int[] dp = new int[n];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < n; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }

            return dp[n - 1];
        }
    }


}
