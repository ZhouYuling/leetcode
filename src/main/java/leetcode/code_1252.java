package leetcode;

public class code_1252 {

    class Solution {
        public int oddCells(int m, int n, int[][] indices) {

            int res = 0;
            int[][] matrix = new int[m][n];
            for (int[] index : indices) {
                ;
                for (int i = 0; i < n; i++) {
                    matrix[index[0]][i]++;
                }
                for (int j = 0; j < m; j++) {
                    matrix[j][index[1]]++;
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if ((matrix[i][j] & 1) != 0) res ++;
                }
            }


            return res;
        }
    }

}
