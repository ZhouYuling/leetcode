public class code_055 {

    static public class Solution {
        public boolean canJump(int[] nums) {
            int n = nums.length;
            int rightmost = 0;
            for (int i = 0; i < n; ++i) {
                if (i <= rightmost) {
                    rightmost = Math.max(rightmost, i + nums[i]);
                    if (rightmost >= n - 1) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = {2,3,1,1,4};
        boolean b = solution.canJump(nums);
        System.out.println(b);


    }

}
