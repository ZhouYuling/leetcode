package leetcode;

import java.util.Arrays;

public class code_322 {

    //递归方法
    public static class Solution2 {

        //程序入口
        public int coinChange(int[] coins, int amount) {
            if (amount < 1) return 0;
            return coinChange(coins, amount, new int[amount]);
        }

        //放回需要多少枚硬币
        private int coinChange(int[] coins, int rem, int[] count) {
            //总金额小于0，返回-1
            if (rem < 0) return -1;
            //总金额为0，返回0
            if (rem == 0) return 0;
            //已经计算了最小需要多少硬币，直接返回即可，这个情况是很有可能发生的
            if (count[rem - 1] != 0) return count[rem - 1];
            //初始化最小值
            int min = Integer.MAX_VALUE;
            //循环所有可能的硬币组合
            for (int coin : coins) {
                int res = coinChange(coins, rem - coin, count);
                //显然如果res是最优解，硬币数+1
                if (res >= 0 && res < min)
                    min = 1 + res;
            }
            //求得该点的最少硬币数
            count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
            return count[rem - 1];
        }
    }

    //动态规划
    public class Solution {
        public int coinChange(int[] coins, int amount) {
            int max = amount + 1;
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, max);
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
                //多个循环
                for (int j = 0; j < coins.length; j++) {
                    if (coins[j] <= i) {
                        //对于这为什么要加一个min(dp[i],),加一个dp[i],因为是多个硬币中取一个，dp[i]是当成一个值进行传递
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];
        }
    }

}
