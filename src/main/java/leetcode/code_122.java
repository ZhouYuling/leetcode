package leetcode;

public class code_122 {

    class Solution {
        // dp[i][0]第i天手里没有股票的最高收益,d[i][1]第i天手里有一只股票的最高受益
        // 定义状态转移方程
        // dp[i][0] = max{dp[i - 1][0], dp[i - 1][1] + prices[i]}
        // dp[i][1] = max{dp[i - 1][1], dp[i - 1][0] - prices[i]}
        // 初始值dp[0][0]=0,dp[0][1]=-prices[0]
        public int maxProfit(int[] prices) {
            int n = prices.length;
            if (n <= 1) return 0;
            int[][] dp = new int[n][2];
            dp[0][0]=0;
            dp[0][1]=-prices[0];
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }

            return dp[n-1][0];
        }
    }

    // 贪心算法
    class Solution2 {
        public int maxProfit(int[] prices) {
            int ans = 0;
            int n = prices.length;
            for (int i = 1; i < n; ++i) {
                ans += Math.max(0, prices[i] - prices[i - 1]);
            }
            return ans;
        }
    }


}
