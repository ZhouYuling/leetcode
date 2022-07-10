package leetcode;

public class code_300 {

    //动态规划
    class Solution {
        public int lengthOfLIS(int[] nums) {
            //边界
            if (nums.length == 0) {
                return 0;
            }
            //赋初值
            int[] dp = new int[nums.length];
            dp[0] = 1;
            int maxans = 1;
            //从1开始遍历，避免越界
            for (int i = 1; i < nums.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        //状态转移方程
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                maxans = Math.max(maxans, dp[i]);
            }
            return maxans;
        }
    }

    //贪心+二分查找
    class Solution2 {
        public int lengthOfLIS(int[] nums) {
            int len = 1, n = nums.length;
            if (n == 0) {
                return 0;
            }
            // 定义一个数组 表示长度为 i 的最长上升子序列的末尾元素的最小值
            // 这个i表示当前数组实际存有i个元素，长度增加的田间是除非比数组最后面一个元素大
            int[] d = new int[n + 1];
            d[len] = nums[0];
            for (int i = 1; i < n; ++i) {
                // 当前位置的数比数组中最后一个大，记录当前值
                if (nums[i] > d[len]) {
                    d[++len] = nums[i];
                } else {
                    // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                    int l = 1, r = len, pos = 0;
                    while (l <= r) {
                        int mid = (l + r) >> 1;
                        if (d[mid] < nums[i]) {
                            pos = mid;
                            l = mid + 1;
                        } else {
                            r = mid - 1;
                        }
                    }
                    d[pos + 1] = nums[i];
                }
            }
            return len;
        }
    }


}
