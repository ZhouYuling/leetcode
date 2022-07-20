package offer;

public class offer_39 {

    class Solution {
        public int majorityElement(int[] nums) {
            int x = 0, votes = 0;
            for(int num : nums){
                if(votes == 0) x = num;
                // 因为题目给的条件，有一个数字出现的次数超过数组长度的一半
                // 那么可以中和掉其他所有元素
                votes += num == x ? 1 : -1;
            }
            return x;
        }
    }

    public static void main(String[] args) {
        offer_39 code = new offer_39();
        Solution solution = code.new Solution();
        System.out.println(solution.majorityElement(new int[]{1,2,3,2,2,2,5,4,2}));
    }

    // 其他方法如hash
    // 排序
    // 随机数法
    // 分治
    class Solution2 {

        private int countInRange(int[] nums, int num, int lo, int hi){
            int count = 0;
            for (int i = lo; i <= hi; i ++)
                if (nums[i] == num) count++;
            return count;
        }

        public int majorityElement(int[] nums) {
            return majorityElementRec(nums, 0, nums.length - 1);
        }

        private int majorityElementRec(int[] nums, int lo, int hi) {
            if (lo == hi) return nums[lo];

            int mid = (hi - lo) / 2 + lo;
            int left = majorityElementRec(nums, lo, mid);
            // 这个地方—+1一定要注意，不然会造成堆栈溢出异常
            int right = majorityElementRec(nums, mid + 1, hi);
            if (left == right) return left;

            int leftCount = countInRange(nums, left, lo, hi);
            int rightCount = countInRange(nums, right, lo, hi);
            return leftCount > rightCount ? left : right;
        }
    }

}
