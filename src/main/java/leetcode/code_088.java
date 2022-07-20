package leetcode;

import java.util.Arrays;

public class code_088 {

    // 暴力，直接合并后排序
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            for (int i = 0; i < nums2.length; i++) {
                nums1[m + i] = nums2[i];
            }
            Arrays.sort(nums1);
        }
    }

    // 双指针，从头开始排序
    class Solution2 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int p0 = 0, p1 = 0;
            int[] sorted = new int[m + n];
            int curr;
            while (p0 != m || p1 != n) {
                if (p0 == m) curr = nums2[p1 ++];
                else if (p1 == n) curr = nums1[p0 ++];
                else if (nums1[p0] < nums2[p1]) curr = nums1[p0 ++];
                else curr = nums2[p1 ++];
                sorted[p0 + p1 - 1] = curr;
            }
            for (int i = 0; i < sorted.length; i++) {
                nums1[i] = sorted[i];
            }
        }
    }

    // 双指针，从尾到头
    class Solution3 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int p1 = m - 1, p2 = n - 1;
            int curr;
            int tail = m + n - 1;
            while (p1 >= 0 || p2 >= 0) {
                if (p1 == -1) curr = nums2[p2 --];
                else if (p2 == -1) curr = nums1[p1 --];
                else if (nums1[p1] > nums2[p2]) curr = nums1[p1 --];
                else curr = nums2[p2 --];
                nums1[tail --] = curr;
            }
        }
    }


}
