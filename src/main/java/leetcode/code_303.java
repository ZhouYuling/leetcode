package leetcode;

public class code_303 {

    class NumArray {
        int[] target;
        public NumArray(int[] nums) {
            target = nums;
        }

        public int sumRange(int left, int right) {
            if(left < 0 || right >= target.length || left > right){
                return -1;
            }
            int res = 0;
            for (int i = left; i <= right; i ++)
                res += target[i];

            return res;
        }
    }

    class NumArray2 {
        int[] sum;
        public void NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            int l = left < 0 ? 0 : left > right ? Math.min(right, sum.length) : left;
            int r = right >= sum.length ? right - 1 : right < left ? Math.max(left, 0) : right;

            return sum[r + 1] - sum[l];
        }
    }

}
