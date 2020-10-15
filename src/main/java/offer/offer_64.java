package offer;

public class offer_64 {

    static class Solution {
        public int sumNums(int n) {
            boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
            return n;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int i = solution.sumNums(10);
        System.out.println(i);

    }

    static class Solution1 {
        public int sumNums(int n) {
            int ans = 0, A = n, B = n + 1;
            boolean flag;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            flag = ((B & 1) > 0) && (ans += A) > 0;
            A <<= 1;
            B >>= 1;

            return ans >> 1;
        }
    }

}
