package leetcode;

public class code_461 {

    class Solution {
        public int hammingDistance(int x, int y) {
            return Integer.bitCount(x ^ y);
        }
    }

    static class Solution1 {
        public int hammingDistance(int x, int y) {
            int xor = x ^ y;
            int distance = 0;
            while (xor != 0) {
                if (xor % 2 == 1)
                    distance += 1;
                xor = xor >> 1;
            }
            return distance;
        }
    }


}
