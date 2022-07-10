package leetcode;

import java.util.HashMap;

public class code_560 {

    public static class Solution {
        public int subarraySum(int[] nums, int k) {
            //pre表示前n个数总和
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
        int[] nums = {3, 4, 7, 2, -3, 1, 4, 2};
        int k = 7;
        int i = solution.subarraySum(nums, k);
        System.out.println(i);

    }


    public int subarraySum1(int[] nums, int k) {
        //记录子数组个数
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            //记录子数组之和
            int sum = 0;
            //从start开始找
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

}
