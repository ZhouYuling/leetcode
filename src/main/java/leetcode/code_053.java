package leetcode;

public class code_053 {

    public int maxSubArray(int[] nums) {

        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;

    }

    static class Solution {
        // 线段树
        public class Status {
            public int lSum, rSum, mSum, iSum;

            public Status(int lSum, int rSum, int mSum, int iSum) {
                this.lSum = lSum;
                this.rSum = rSum;
                this.mSum = mSum;
                this.iSum = iSum;
            }
        }

        public int maxSubArray(int[] nums) {
            return getInfo(nums, 0, nums.length - 1).mSum;
        }

        public Status getInfo(int[] a, int l, int r) {
            if (l == r) {
                return new Status(a[l], a[l], a[l], a[l]);
            }
            int m = (l + r) >> 1;
            Status lSub = getInfo(a, l, m);
            Status rSub = getInfo(a, m + 1, r);
            return pushUp(lSub, rSub);
        }

        public Status pushUp(Status l, Status r) {
            // iSum 表示 [l,r] 的区间和
            int iSum = l.iSum + r.iSum;
            // lSum 表示 [l,r] 内以 l 为左端点的最大子段和
            int lSum = Math.max(l.lSum, l.iSum + r.lSum);
            // rSum 表示 [l,r] 内以 r 为右端点的最大子段和
            int rSum = Math.max(r.rSum, r.iSum + l.rSum);
            // mSum 表示 [l,r] 内的最大子段和
            int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
            return new Status(lSum, rSum, mSum, iSum);
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int i = solution.maxSubArray(nums);
        System.out.println(i);

    }

    // 手写动态
    public int maxSubArray2(int[] nums) {

        int pre = 0,maxAns = nums[0];
        for (int num: nums) {
            pre = Math.max(num, num + pre);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;

    }

}
