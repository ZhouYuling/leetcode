package leetcode;

public class code_191 {

    public class Solution {
        public int hammingWeight(int n) {
            int ret = 0;
            for (int i = 0; i < 32; i++) {
                if ((n & (1 << i)) != 0) {
                    ret++;
                }
            }
            return ret;
        }
    }

    public class Solution2 {
        public int hammingWeight(int n) {
            int ret = 0;
            while (n != 0) {
                n &= n - 1;
                ret ++;
            }
            return ret;
        }
    }

}
