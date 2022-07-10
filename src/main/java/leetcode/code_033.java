package leetcode;

public class code_033 {

    public int search(int[] nums, int target) {

        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : 1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target)
                return mid;
            // 序列[0, mid]有序，且升序
            if (nums[0] <= nums[mid]){
                // 目标值在[0, mid]中，而且有序
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                }else {
                    l = mid + 1;
                }
            } else {
                // 范围[mid, n - 1]有序，且升序, 且目标值在[mid, n - 1]中
                if (nums[mid] < target && target <= nums[n - 1])
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }

        return -1;
    }
}
