package leetcode;

public class code_416 {

    class Solution {
        public boolean canPartition(int[] nums) {
            //边界值
            int n = nums.length;
            if (n < 2) {
                return false;
            }
            //求和、求最大值
            int sum = 0, maxNum = 0;
            for (int num : nums) {
                sum += num;
                maxNum = Math.max(maxNum, num);
            }
            //总数不为偶数，则不能分割
            if (sum % 2 != 0) {
                return false;
            }
            //除了maxNum以外的所有元素之和一定小于target，因此不可能将数组分割成元素和相等的两个子集
            int target = sum / 2;
            if (maxNum > target) {
                return false;
            }
            //边界条件
            boolean[][] dp = new boolean[n][target + 1];
            for (int i = 0; i < n; i++) {
                dp[i][0] = true;
            }
            dp[0][nums[0]] = true;
            //第i个位置的数字是否选择
            for (int i = 1; i < n; i++) {
                int num = nums[i];
                //j表示当前背包里面的重量
                for (int j = 1; j <= target; j++) {
                    //状态转移方程
                    if (j >= num) {
                        dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[n - 1][target];
        }
    }

}
