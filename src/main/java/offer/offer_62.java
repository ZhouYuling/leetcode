package offer;

import java.util.ArrayList;

public class offer_62 {

    // 剑指 Offer 62. 圆圈中最后剩下的数字
    // 官方给的答案都理解不了，下面的这个答案看起来还像那么回事
    class Solution {
        public int lastRemaining(int n, int m) {
            ArrayList<Integer> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(i);
            }
            int idx = 0;
            while (n > 1) {
                idx = (idx + m - 1) % n;
                list.remove(idx);
                n--;
            }
            return list.get(0);
        }
    }

    public static void main(String[] args) {



    }

}
