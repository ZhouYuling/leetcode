package leetcode;

import java.util.Arrays;
import java.util.Stack;

public class code_581 {

    public static class Solution {
        public int findUnsortedSubarray(int[] nums) {
            Stack< Integer > stack = new Stack< Integer >();
            int l = nums.length, r = 0;
            for (int i = 0; i < nums.length; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                    l = Math.min(l, stack.pop());
                stack.push(i);
            }
            stack.clear();
            for (int i = nums.length - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                    r = Math.max(r, stack.pop());
                stack.push(i);
            }
            return r - l > 0 ? r - l + 1 : 0;
        }
    }
    public int findUnsortedSubarray1(int[] nums) {
        if (isSorted(nums)) {
            return 0;
        }
        //维护一个全局排序的数组
        int[] numsSorted = new int[nums.length];
        //System.arraycopy比copy和clone都快
        System.arraycopy(nums, 0, numsSorted, 0, nums.length);
        //数组排序
        Arrays.sort(numsSorted);
        //确定左边界
        int left = 0;
        while (nums[left] == numsSorted[left]) {
            left++;
        }
        //确定右边界
        int right = nums.length - 1;
        while (nums[right] == numsSorted[right]) {
            right--;
        }
        return right - left + 1;
    }

    public boolean isSorted(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public int findUnsortedSubarray2(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = 0; i < n; i++) {
            //取左侧单调结束的下标
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            //取右侧单调结束的下标
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }

    public static void main(String[] args) {


        int unsortedSubarray2 = new code_581().findUnsortedSubarray2(new int[]{2, 6, 4, 8, 10, 9, 15});
        System.out.println(unsortedSubarray2);

    }

}
