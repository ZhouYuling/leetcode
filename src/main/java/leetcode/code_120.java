package leetcode;

import java.util.List;

public class code_120 {

    // 手写动态规划
    class Solution {
        // dp[i][j]第i层第j个位置的最小路径和
        // dp[i][j] = Math.mix(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j];
        //
        public int minimumTotal(List<List<Integer>> triangle) {

            if (triangle == null || triangle.size() == 0) return 0;
            int m = triangle.size();
            int n = triangle.get(0).size();
            for (int i = 0; i < m; i++) {

            }


            return 0;
        }
    }

}
