package leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class code_1331 {

    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        int[] sortArr = new int[n];
        System.arraycopy(arr, 0, sortArr, 0, n);
        Arrays.sort(sortArr);
        HashMap<Integer, Integer> ranks = new HashMap<>();
        int[] ans = new int[arr.length];
        for (int a : sortArr) {
            if (!ranks.containsKey(a)) {
                ranks.put(a, ranks.size() + 1);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            ans[i] = ranks.get(arr[i]);
        }
        return ans;
    }

}
