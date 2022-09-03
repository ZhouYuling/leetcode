package leetcode;

import java.util.Arrays;

public class code_034 {

    public int[] searchRange(int[] nums, int target){

        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        // 可能target不存在于数组中
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target){
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};

    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            // 取左侧第一个等于target，用nums[mid]>=target
            // 取右侧第一个大于target，用nums[mid]>target，实际计算结果是大于target的第一个值，注意第5行减一
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            }else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1, 2, 3, 3, 3, 4, 5, 6, 7};
        code_034 code = new code_034();
        int[] res = code.searchRange(nums, 3);
        System.out.println(Arrays.toString(res));

        int i = code.binarySearch(nums, 3, false);
        System.out.println(i);

        int j = code.binarySearchLeftLastLowNum(nums, 3);
        System.out.println(j);

        int m = code.binarySearchRightFistEqualNum(nums, 3);
        System.out.println(m);

        int x=0,y=3;
        System.out.println(x);
        System.out.println(y);

        System.out.println("--------------");
        System.out.println(code.binarySearchLeftLastLowNum(nums, 3));
        System.out.println(code.binarySearchRightFistEqualNum(nums, 3));
        System.out.println(code.binarySearchRightFistEqualNum2(nums, 3));

    }

    public int binarySearchLeftLastLowNum(int[] nums, int target) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
                ans = mid;
            }else {
                right = mid - 1;
            }
        }
        return ans;
    }


    public int binarySearchRightFistEqualNum(int[] nums, int target) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
                ans = mid;
            }else {
                right = mid - 1;
            }
        }
        return ans;
    }

    // 测试ans放在另外一个判断中
    public int binarySearchRightFistEqualNum2(int[] nums, int target) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            }else {
                right = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }


}
