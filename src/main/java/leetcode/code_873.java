package leetcode;

import java.util.HashSet;
import java.util.Set;

public class code_873 {

    public int lenLongestFibSubseq(int[] A) {

        int n = A.length;
        Set<Integer> set = new HashSet<>();
        for (int a :
                A) {
            set.add(a);
        }

        int res = 2;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x = A[i], y = A[i] + A[j];
                int length = 2;
                while (set.contains(y)) {
                    int tmp = x;
                    x = y;
                    y = tmp + x;
                    res = Math.max(res,  ++length);
                };
            }
        }


        return res >= 3 ? res : 0;
    }

    public static void main(String[] args) {
        code_873 code_873 = new code_873();
        int i = code_873.lenLongestFibSubseq(new int[]{2, 4, 7, 8, 9, 10, 14, 15, 18, 23, 32, 50});
        System.out.println(i);
    }

}
