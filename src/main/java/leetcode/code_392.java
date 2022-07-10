package leetcode;

public class code_392 {

    class Solution {
        public boolean isSubsequence(String s, String t) {
            int n = s.length(), m = t.length();
            int i = 0, j = 0;
            while (i < n && j < m) {
                if (s.charAt(i) == t.charAt(j)) {
                    i++;
                }
                j++;
            }
            return i == n;
        }
    }


    // 动态规划，在多个s串进行比较的时候，这个方法有优势
    class Solution2 {
        public boolean isSubsequence(String s, String t) {
            int n = s.length(), m = t.length();

            int[][] f = new int[m + 1][26];
            for (int i = 0; i < 26; i++) {
                f[m][i] = m;
            }

            for (int i = m - 1; i >= 0; i--) {
                for (int j = 0; j < 26; j++) {
                    if (t.charAt(i) == j + 'a')
                        f[i][j] = i;
                    else
                        f[i][j] = f[i + 1][j];
                }
            }
            int add = 0;
            for (int i = 0; i < n; i++) {
                if (f[add][s.charAt(i) - 'a'] == m) {
                    return false;
                }
                add = f[add][s.charAt(i) - 'a'] + 1;
            }
            return true;
        }
    }

    // 另外一个方法是二分查找法


    public static void main(String[] args) {

        ThreadLocal tl = new ThreadLocal();

    }



}
