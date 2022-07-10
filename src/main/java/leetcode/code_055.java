package leetcode;

public class code_055 {

    static public class Solution {
        public boolean canJump(int[] nums) {
            int n = nums.length;
            //最远能跳的距离
            int rightmost = 0;
            for (int i = 0; i < n; ++i) {
                //是否能跳到这一步
                if (i <= rightmost) {
                    //取当前位置能跳最远距离，和最远距离最大值
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

    public boolean canJump(int[] nums) {
        int reach = 0, n = nums.length;
        for (int i = 0; i <= reach && reach < n - 1; i++) {
            reach = Math.max(reach, i + nums[i]);
        }

        return reach >= n - 1;
    }

}
