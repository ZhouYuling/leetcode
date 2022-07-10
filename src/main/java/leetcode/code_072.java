package leetcode;

public class code_072 {

    static class Solution {
        public int minDistance(String word1, String word2) {
            int n = word1.length();
            int m = word2.length();

            // 有一个字符串为空串
            if (n * m == 0) {
                return n + m;
            }

            // DP 数组
            int[][] D = new int[n + 1][m + 1];

            // 边界状态初始化
            for (int i = 0; i < n + 1; i++) {
                D[i][0] = i;
            }
            for (int j = 0; j < m + 1; j++) {
                D[0][j] = j;
            }

            // 计算所有 DP 值
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < m + 1; j++) {
                    // A的i位置增加一个字符，和B的0-j保持一致
                    int left = D[i - 1][j] + 1;
                    // B的j位置增加一个字符，和A的0-i保持一致
                    int down = D[i][j - 1] + 1;
                    // A的i位置替换成B的j位置的字符，如果两个位置的字符相等，我们也可以不做处理
                    int left_down = D[i - 1][j - 1];
                    if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                        left_down += 1;
                    }
                    D[i][j] = Math.min(left, Math.min(down, left_down));
                }
            }
            return D[n][m];
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        String word1 = "helllo";
        String word2 = "hello2";
        int i = solution.minDistance(word1, word2);
        System.out.println(i);

    }

}
