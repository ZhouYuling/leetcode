package leetcode;

import java.util.Arrays;

public class code_312 {

    //记忆化搜索
    static class Solution {
        //存放最大值int[i][j] i为左编号，j为右编号
        public int[][] rec;
        //两侧为1
        public int[] val;

        public int maxCoins(int[] nums) {
            //数组长度
            int n = nums.length;
            val = new int[n + 2];
            for (int i = 1; i <= n; i++) {
                val[i] = nums[i - 1];
            }
            //赋值
            val[0] = val[n + 1] = 1;
            rec = new int[n + 2][n + 2];
            //全部赋值为-1
            for (int i = 0; i <= n + 1; i++) {
                Arrays.fill(rec[i], -1);
            }
            return solve(0, n + 1);
        }

        public int solve(int left, int right) {
            //开区间没有气球，返回为0
            if (left >= right - 1) {
                return 0;
            }
            //存储计算结果，减少时间复杂度
            if (rec[left][right] != -1) {
                return rec[left][right];
            }
            //i表示中点i
            for (int i = left + 1; i < right; i++) {
                int sum = val[left] * val[i] * val[right];
                //左侧最大值 + 右侧最大值
                sum += solve(left, i) + solve(i, right);
                rec[left][right] = Math.max(rec[left][right], sum);
            }
            return rec[left][right];
        }
    }

    //动态规划
    static class Solution3 {
        public int maxCoins(int[] nums) {
            int n = nums.length;
            int[][] rec = new int[n + 2][n + 2];
            int[] val = new int[n + 2];
            //初始化，赋值
            val[0] = val[n + 1] = 1;
            for (int i = 1; i <= n; i++) {
                val[i] = nums[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 2; j <= n + 1; j++) {
                    for (int k = i + 1; k < j; k++) {
                        int sum = val[i] * val[k] * val[j];
                        sum += rec[i][k] + rec[k][j];
                        rec[i][j] = Math.max(rec[i][j], sum);
                    }
                }
            }
            return rec[0][n + 1];
        }
    }


    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = {3,1,5,8};
        int i = solution.maxCoins(nums);
        System.out.println(i);

        Solution3 solution3 = new Solution3();
        int i3 = solution3.maxCoins(nums);
        System.out.println(i3);

    }

}
