package leetcode;

import java.util.Arrays;

public class code_977 {

    //平方以后再升序
    static class Solution {
        public int[] sortedSquares(int[] A) {
            int[] ans = new int[A.length];
            for (int i = 0; i < A.length; ++i) {
                ans[i] = A[i] * A[i];
            }
            Arrays.sort(ans);
            return ans;
        }
    }

    static class Solution1 {
        public int[] sortedSquares(int[] A) {
            int n = A.length;
            int negative = -1;
            for (int i = 0; i < n; ++i) {
                if (A[i] < 0) {
                    negative = i;
                } else {
                    break;
                }
            }

            int[] ans = new int[n];
            int index = 0, i = negative, j = negative + 1;
            while (i >= 0 || j < n) {
                //如果没有负数
                if (i < 0) {
                    ans[index] = A[j] * A[j];
                    ++j;
                } else if (j == n) { //如果i>0，同时j到最右侧
                    ans[index] = A[i] * A[i];
                    --i;
                } else if (A[i] * A[i] < A[j] * A[j]) {//右侧平方值大于左侧平方值
                    ans[index] = A[i] * A[i];
                    --i;
                } else {//左侧平方值大于右侧平方值
                    ans[index] = A[j] * A[j];
                    ++j;
                }
                ++index;
            }

            return ans;
        }
    }

    class Solution3 {
        public int[] sortedSquares(int[] A) {
            int n = A.length;
            int[] ans = new int[n];
            for (int i = 0, j = n - 1, pos = n - 1; i <= j;) {
                if (A[i] * A[i] > A[j] * A[j]) {
                    ans[pos] = A[i] * A[i];
                    ++i;
                } else {
                    ans[pos] = A[j] * A[j];
                    --j;
                }
                --pos;
            }
            return ans;
        }
    }

    public static void main(String[] args) {

    }

}
