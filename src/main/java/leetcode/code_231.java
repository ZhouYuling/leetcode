package leetcode;

public class code_231 {

    class Solution {
        public boolean isPowerOfTwo(int n) {
            if (n == 0) return false;
            if (n == 1) return true;
            if (n % 2 != 0) return false;
            return isPowerOfTwo(n / 2);
        }
    }

    class Solution2 {
        public boolean isPowerOfTwo(int n) {
            return n > 0 && (n & (n - 1)) == 0;
        }
    }

    class Solution3 {
        public boolean isPowerOfTwo(int n) {
            return n > 0 && (n & -n) == n;
        }
    }

}
