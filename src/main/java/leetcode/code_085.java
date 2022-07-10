package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class code_085 {

    static class Solution {
        public int maximalRectangle(char[][] matrix) {

            if (matrix.length == 0) return 0;
            int maxarea = 0;
            int[][] dp = new int[matrix.length][matrix[0].length];

            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[0].length; j++){
                    if (matrix[i][j] == '1'){

                        // compute the maximum width and update dp with it
                        dp[i][j] = j == 0? 1 : dp[i][j-1] + 1;

                        int width = dp[i][j];

                        // compute the maximum area rectangle with a lower right corner at [i, j]
                        for(int k = i; k >= 0; k--){
                            width = Math.min(width, dp[k][j]);
                            maxarea = Math.max(maxarea, width * (i - k + 1));
                        }
                    }
                }
            }
            return maxarea;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        int i = solution.maximalRectangle(matrix);
        System.out.println(i);

    }

    //使用柱状图的优化暴力方法
    class Solution1 {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length;
            //边界值
            if (m == 0) {
                return 0;
            }
            int n = matrix[0].length;
            int[][] left = new int[m][n];

            //left[i][j] 为矩阵第 ii 行第 jj 列元素的左边连续 11 的数量
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                    }
                }
            }

            int ret = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '0') {
                        continue;
                    }
                    int width = left[i][j];
                    int area = width;
                    //从下往上遍历所有面积，取最大值
                    for (int k = i - 1; k >= 0; k--) {
                        width = Math.min(width, left[k][j]);
                        area = Math.max(area, (i - k + 1) * width);
                    }
                    ret = Math.max(ret, area);
                }
            }
            return ret;
        }
    }

    //单调栈
    class Solution2 {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length;
            if (m == 0) {
                return 0;
            }
            int n = matrix[0].length;
            int[][] left = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                    }
                }
            }

            int ret = 0;
            for (int j = 0; j < n; j++) { // 对于每一列，使用基于柱状图的方法
                int[] up = new int[m];
                int[] down = new int[m];

                Deque<Integer> stack = new LinkedList<Integer>();
                //取向上取连续最大的那个数
                for (int i = 0; i < m; i++) {
                    while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                        stack.pop();
                    }
                    up[i] = stack.isEmpty() ? -1 : stack.peek();
                    stack.push(i);
                }
                stack.clear();
                //向下取连续最大的那个数
                for (int i = m - 1; i >= 0; i--) {
                    while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                        stack.pop();
                    }
                    down[i] = stack.isEmpty() ? m : stack.peek();
                    stack.push(i);
                }

                for (int i = 0; i < m; i++) {
                    //down[i]和up[i]都会比left[i][j]大
                    int height = down[i] - up[i] - 1;
                    int area = height * left[i][j];
                    ret = Math.max(ret, area);
                }
            }
            return ret;
        }
    }


}
