package leetcode;

public class code_461 {

    //内置位计数功能
    class Solution {
        public int hammingDistance(int x, int y) {
            return Integer.bitCount(x ^ y);
        }
    }

    static class Solution1 {
        public int hammingDistance(int x, int y) {
            //x和y异或，获得位不相等
            int xor = x ^ y;
            //距离
            int distance = 0;
            //遍历
            while (xor != 0) {
                //取最后一个bit，等于1则距离+1
                if (xor % 2 == 1)
                    distance += 1;
                //位右移动一位
                xor = xor >> 1;
            }
            return distance;
        }
    }


}
