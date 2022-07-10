package leetcode;

public class code_338 {

    //Brian Kernighan 算法
    class Solution {
        public int[] countBits(int n) {
            //除1-n个数，另外多一个0，所以n+1
            int[] bits = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                bits[i] = countOnes(i);
            }
            return bits;
        }

        public int countOnes(int x) {
            int ones = 0;
            while (x > 0) {
                x &= (x - 1);
                ones++;
            }
            return ones;
        }
    }

    //动态规划
    class Solution2 {
        public int[] countBits(int n) {
            int[] bits = new int[n + 1];
            int highBit = 0;
            for (int i = 1; i <= n; i++) {
                //2的整数倍，那么只有1个1
                if ((i & (i - 1)) == 0) {
                    highBit = i;
                }
                //其他条件上一个数+1即可
                bits[i] = bits[i - highBit] + 1;
            }
            return bits;
        }
    }

    //最低有效位 x除以22的余数可以通过x&1得到
    class Solution3 {
        public int[] countBits(int n) {
            int[] bits = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                bits[i] = bits[i >> 1] + (i & 1);
            }
            return bits;
        }
    }

    class Solution4 {
        public int[] countBits(int n) {
            int[] bits = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                bits[i] = bits[i & (i - 1)] + 1;
            }
            return bits;
        }
    }



}
