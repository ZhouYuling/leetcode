package offer;

public class offer_15 {

    public class Solution {
        public int hammingWeight(int n) {
            int ret = 0;
            while (n != 0) {
                n &= n - 1;
                ret++;
            }
            return ret;
        }
    }

    // 这种做法是错误的,如下期望结果是3，结果如下运算是4
    // 00000000000000000000000000001011
    public class Solution2 {
        public int hammingWeight(int n) {
            int ret = 0;
            while (n != 0) {
                n >>= 1;
                ret++;
            }
            return ret;
        }
    }

    public class Solution3 {
        public int hammingWeight(int n) {
            int ret = 0;
            // 题目给的条件，最大长度是32位
            for (int i = 0; i < 32; i++) {
                // 获取每一位的值
                if ((n & (1 << i)) != 0) {
                    ret++;
                }
            }
            return ret;
        }
    }


}
