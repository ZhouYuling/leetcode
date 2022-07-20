package offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class offer_57 {

    // 剑指 Offer 57 - II. 和为s的连续正数序列
    static class Solution {
        public int[][] findContinuousSequence(int target) {
            int i = 1; // 滑动窗口的左边界
            int j = 1; // 滑动窗口的右边界
            int sum = 0; // 滑动窗口中数字的和
            List<int[]> res = new ArrayList<>();

            while (i <= target / 2) {
                if (sum < target) {
                    // 右边界向右移动
                    sum += j;
                    j++;
                } else if (sum > target) {
                    // 左边界向右移动
                    sum -= i;
                    i++;
                } else {
                    // 记录结果
                    int[] arr = new int[j-i];
                    for (int k = i; k < j; k++) {
                        arr[k-i] = k;
                    }
                    res.add(arr);
                    // 左边界向右移动
                    sum -= i;
                    i++;
                }
            }

            return res.toArray(new int[res.size()][]);
        }

    }

    // 剑指 Offer 57 - II. 和为s的连续正数序列
    class Solution11 {
        public int[][] findContinuousSequence(int target) {
            List<int[]> vec = new ArrayList<int[]>();
            // 注意右边界的起始位置
            for (int l = 1, r = 2; l < r;) {
                int sum = (l + r) * (r - l + 1) / 2;
                if (sum == target) {
                    int[] res = new int[r - l + 1];
                    for (int i = l; i <= r; ++i) {
                        res[i - l] = i;
                    }
                    vec.add(res);
                    l++;
                } else if (sum < target) {
                    r++;
                } else {
                    l++;
                }
            }
            return vec.toArray(new int[vec.size()][]);
        }
    }

    // 剑指 Offer 57. 和为s的两个数字
    class Solution2 {
        public int[] twoSum(int[] nums, int target) {

            HashSet<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (set.contains(target - num)) return new int[]{num, target - num};
                else set.add(num);
            }


            return new int[0];
        }
    }

    // 剑指 Offer 57. 和为s的两个数字
    class Solution3 {
        public int[] twoSum(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) return new int[]{nums[left], nums[right]};
                if (sum < target) left ++;
                if (sum > target) right --;
            }


            return new int[0];
        }
    }


    public static void main(String[] args) {

        Solution solution = new Solution();
        int[][] continuousSequence = solution.findContinuousSequence(9);
        for (int[] ints : continuousSequence) {
            System.out.println(Arrays.toString(ints));
        }

    }

}
