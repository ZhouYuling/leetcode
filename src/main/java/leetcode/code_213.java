package leetcode;

public class code_213 {

    class Solution {
        public int rob(int[] nums) {
            int length = nums.length;
            if (length == 1) {
                return nums[0];
            } else if (length == 2) {
                return Math.max(nums[0], nums[1]);
            }
            //如果不偷窃最后一间房屋，则偷窃房屋的下标范围是 [0, n-2][0,n−2]；如果不偷窃第一间房屋，则偷窃房屋的下标范围是 [1, n-1][1,n−1]
            return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
        }

        public int robRange(int[] nums, int start, int end) {
            int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
            for (int i = start + 2; i <= end; i++) {
                int temp = second;
                second = Math.max(first + nums[i], second);
                first = temp;
            }
            return second;
        }
    }




}
