package offer;

public class offer_16 {

    // 迭代
    // k神的题解：https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/solution/mian-shi-ti-16-shu-zhi-de-zheng-shu-ci-fang-kuai-s/
    class Solution {
        public double myPow(double x, int n) {
            if (x == 0) return 0;
            long b = n;
            double res = 1.0;

            if (n < 0) {
                x = 1 / x;
                b = -b;
            }
            while (b > 0) {
                if ((b & 1) == 1) res *= x;
                x *= x;
                b >>= 1;
            }

            return res;
        }
    }

    // 递归
    class Solution2 {
        public double myPow(double x, int n) {
            long b = n;
            return b > 0 ? quickMul(x, b) : quickMul(1 / x, -b);
        }

        private double quickMul(double x, long b) {
            if (b == 0) return 1.0;
            double y = quickMul(x, b / 2);
            return b % 2 == 0 ? y * y : y * y * x;
        }
    }

}
