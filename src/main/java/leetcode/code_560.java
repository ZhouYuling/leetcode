package leetcode;

import java.util.HashMap;

public class code_560 {

    public static class Solution {
        public int subarraySum(int[] nums, int k) {
            int count = 0, pre = 0;
            HashMap< Integer, Integer > mp = new HashMap < > ();
            mp.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                pre += nums[i];
                if (mp.containsKey(pre - k)) {
                    count += mp.get(pre - k);
                }
                mp.put(pre, mp.getOrDefault(pre, 0) + 1);
            }
            return count;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = {1,1,1};
        int k = 1;
        int i = solution.subarraySum(nums, k);
        System.out.println(i);

    }

}
