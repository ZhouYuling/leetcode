package leetcode;

public class code_152 {

    static class Solution {
        //动态规划
        public int maxProduct(int[] nums) {
            int length = nums.length;
            //考虑到负负得正得正得情况，我们需要另外维护一个最小的状态，以便获取
            int[] maxF = new int[length];
            int[] minF = new int[length];
            System.arraycopy(nums, 0, maxF, 0, length);
            System.arraycopy(nums, 0, minF, 0, length);
            for (int i = 1; i < length; ++i) {
                //Math.max和Math.min只有两个参数，于是使用这个方法进行判断三个数最小/最大值
                maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
                minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
            }
            //遍历所有最大值，获取全局最大值
            int ans = maxF[0];
            for (int i = 1; i < length; ++i) {
                ans = Math.max(ans, maxF[i]);
            }
            return ans;
        }
    }

    public static void main(String[] sa){
        Solution solution = new Solution();
        int[] nums = {1,2,3,4,5,6,7};
        int i = solution.maxProduct(nums);
        System.out.println(i);
    }

    //动态规划优化空间复杂度
    class Solution2 {
        public int maxProduct(int[] nums) {
            int maxF = nums[0], minF = nums[0], ans = nums[0];
            int length = nums.length;
            for (int i = 1; i < length; ++i) {
                int mx = maxF, mn = minF;
                maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
                minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
                //根据滚动数组的思想，只需要三个变量即可
                ans = Math.max(maxF, ans);
            }
            return ans;
        }
    }


}
