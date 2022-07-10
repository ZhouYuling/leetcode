package leetcode;

import java.util.Arrays;

public class code_209 {

    // 前缀和 + 二分法
    class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            int n = nums.length;
            if (n == 0) {
                return 0;
            }
            int ans = Integer.MAX_VALUE;
            int[] sums = new int[n + 1];
            // 为了方便计算，令 size = n + 1
            // sums[0] = 0 意味着前 0 个元素的前缀和为 0
            // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
            // 以此类推
            for (int i = 1; i <= n; i++) {
                sums[i] = sums[i - 1] + nums[i - 1];
            }
            for (int i = 1; i <= n; i++) {
                int target = s + sums[i - 1];
                // 搜索键的索引，如果它包含在数组中; 否则， (-(insertion point) - 1) 。
                // 如果阵列中的所有元素都小于指定键的第一元件比所述键时，或a.length的索引： 插入点被定义为将键插入到阵列的点。
                // 请注意，这确保当且仅当找到该键时返回值将为> = 0。
                int bound = Arrays.binarySearch(sums, target);
                if (bound < 0) {
                    bound = -bound - 1;
                }
                if (bound <= n) {
                    ans = Math.min(ans, bound - (i - 1));
                }
            }
            return ans == Integer.MAX_VALUE ? 0 : ans;
        }
    }

}
