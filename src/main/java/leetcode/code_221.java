package leetcode;

public class code_221 {

    //动态规划解法
    static class Solution {
        public int maximalSquare(char[][] matrix) {
            int maxSide = 0;
            //边界值
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return maxSide;
            }
            int rows = matrix.length, columns = matrix[0].length;
            int[][] dp = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (matrix[i][j] == '1') {
                        //初始化边界
                        if (i == 0 || j == 0) {
                            dp[i][j] = 1;
                        } else {
                            //状态转移方程
                            dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                        }
                        //更新正方形最长边长
                        maxSide = Math.max(maxSide, dp[i][j]);
                    }
                }
            }
            //更新最长正方形面积
            int maxSquare = maxSide * maxSide;
            return maxSquare;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        int i = solution.maximalSquare(matrix);
        System.out.println(i);

    }


    //暴力法
    class Solution1 {
        public int maximalSquare(char[][] matrix) {
            int maxSide = 0;
            //边界判断
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return maxSide;
            }
            //判断行和列的值
            int rows = matrix.length, columns = matrix[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (matrix[i][j] == '1') {
                        // 遇到一个 1 作为正方形的左上角
                        maxSide = Math.max(maxSide, 1);
                        // 计算可能的最大正方形边长
                        int currentMaxSide = Math.min(rows - i, columns - j);
                        //由于是数组，所以对随机访问提供了遍历
                        for (int k = 1; k < currentMaxSide; k++) {
                            // 判断新增的一行一列是否均为 1
                            boolean flag = true;
                            //检查斜对角是否为1，否则推出循环
                            if (matrix[i + k][j + k] == '0') {
                                break;
                            }
                            //判断currentMaxSide矩阵中的数值是否都为1，否则不成立
                            for (int m = 0; m < k; m++) {
                                if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                    flag = false;
                                    break;
                                }
                            }
                            //更新最大边长
                            if (flag) {
                                maxSide = Math.max(maxSide, k + 1);
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            //计算最大面积
            int maxSquare = maxSide * maxSide;
            return maxSquare;
        }
    }

}
