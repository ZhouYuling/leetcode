package leetcode;

import static utils.Utils.swap;

public class code_075 {

    static class Solution {
        /*
        荷兰三色旗问题解
        */
        public void sortColors(int[] nums) {
            // 对于所有 idx < i : nums[idx < i] = 0
            // j是当前考虑元素的下标
            int p0 = 0, curr = 0;
            // 对于所有 idx > k : nums[idx > k] = 2
            int p2 = nums.length - 1;

            int tmp;
            while (curr <= p2) {
                if (nums[curr] == 0) {
                    // 交换第 p0个和第curr个元素
                    // i++，j++
                    swap(nums, curr, p0);
                    p0++;
                    curr++;
                }
                else if (nums[curr] == 2) {
                    // 交换第k个和第curr个元素
                    // p2--
                    swap(nums, curr, p2);
                    p2--;
                }
                else curr++;
            }
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = {2,0,2,1,1,0};
        solution.sortColors(nums);

    }

}
