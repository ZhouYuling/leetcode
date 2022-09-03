package leetcode;

public class code_1004 {

    public int longestOnes(int[] A, int K) {
        int N = A.length;
        int res = 0;
        int left = 0, right = 0;
        int zeros = 0;
        while (right < N) {
            if (A[right] == 0)
                zeros ++;
            while (zeros > K)
                if (A[left++] == 0)
                    zeros --;
            res = Math.max(res, right - left + 1);
            right ++;
        }
        return res;
    }

}
