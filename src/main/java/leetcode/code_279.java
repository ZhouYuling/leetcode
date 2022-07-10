package leetcode;

import java.util.Arrays;

public class code_279 {


    static class Solution {

        public int numSquares(int n) {
            int dp[] = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            // bottom case
            dp[0] = 0;

            // pre-calculate the square numbers.
            int max_square_index = (int) Math.sqrt(n) + 1;
            int square_nums[] = new int[max_square_index];
            for (int i = 1; i < max_square_index; ++i) {
                square_nums[i] = i * i;
            }

            for (int i = 1; i <= n; ++i) {
                for (int s = 1; s < max_square_index; ++s) {
                    if (i < square_nums[s])
                        break;
                    dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
                }
            }
            return dp[n];
        }
    }

    public static void main(String[] args) {

//        Solution solution = new Solution();
//        int i = solution.numSquares(12);
//        System.out.println(i);

        Solution1 solution1 = new Solution1();
        int i = solution1.numSquares(13);
        System.out.println(i);

    }


    //动态规划
    static class Solution1 {
        public int numSquares(int n) {
            int[] f = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                int minn = Integer.MAX_VALUE;
                for (int j = 1; j * j <= i; j++) {
                    minn = Math.min(minn, f[i - j * j]);
                }
                f[i] = minn + 1;
            }
            return f[n];
        }
    }

    class Solution2 {
        public int numSquares(int n) {
            if (isPerfectSquare(n)) {
                return 1;
            }
            if (checkAnswer4(n)) {
                return 4;
            }
            for (int i = 1; i * i <= n; i++) {
                int j = n - i * i;
                if (isPerfectSquare(j)) {
                    return 2;
                }
            }
            return 3;
        }

        // 判断是否为完全平方数
        public boolean isPerfectSquare(int x) {
            int y = (int) Math.sqrt(x);
            return y * y == x;
        }

        // 判断是否能表示为 4^k*(8m+7)
        public boolean checkAnswer4(int x) {
            while (x % 4 == 0) {
                x /= 4;
            }
            return x % 8 == 7;
        }
    }


}
