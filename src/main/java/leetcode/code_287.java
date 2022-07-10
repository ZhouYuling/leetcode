package leetcode;

public class code_287 {

    // 二分查找
    static class Solution {
        public int findDuplicate(int[] nums) {
            int n = nums.length;
            int l = 1, r = n - 1, ans = -1;
            while (l <= r) {
                int mid = (l + r) >> 1;
                int cnt = 0;
                //其数字都在 [1, n] 范围内（包括 1 和 n）
                for (int i = 0; i < n; ++i) {
                    if (nums[i] <= mid) {
                        cnt++;
                    }
                }
                if (cnt <= mid) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                    ans = mid;
                }
            }
            return ans;

        }
    }

    static class Solution1 {
        public int findDuplicate(int[] nums) {
            int n = nums.length, ans = 0;
            int bit_max = 31;
            while (((n - 1) >> bit_max) == 0) {
                bit_max -= 1;
            }
            for (int bit = 0; bit <= bit_max; ++bit) {
                int x = 0, y = 0;
                for (int i = 0; i < n; ++i) {
                    if ((nums[i] & (1 << bit)) != 0) {
                        x += 1;
                    }
                    if (i >= 1 && ((i & (1 << bit)) != 0)) {
                        y += 1;
                    }
                }
                if (x > y) {
                    ans |= 1 << bit;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {

        int[] nums = {1,3,4,2,2};
        Solution solution = new Solution();
        int duplicate = solution.findDuplicate(nums);
        System.out.println(duplicate);

        Solution1 solution1 = new Solution1();
        int duplicate1 = solution1.findDuplicate(nums);
        System.out.println(duplicate1);

    }

}
