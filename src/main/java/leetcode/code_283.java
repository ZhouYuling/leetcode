package leetcode;

import java.util.Arrays;

public class code_283 {

    static class Solution {
        public void moveZeroes(int[] nums) {
            if(nums==null) {
                return;
            }
            //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
            int j = 0;
            for(int i=0;i<nums.length;++i) {
                if(nums[i] != 0) {
                    nums[j++] = nums[i];
                }
            }
            //非0元素统计完了，剩下的都是0了
            //所以第二次遍历把末尾的元素都赋为0即可
            for(int i=j;i<nums.length;++i) {
                nums[i] = 0;
            }
        }
    }

    class Solution1 {
        public void moveZeroes(int[] nums) {
            if(nums==null) {
                return;
            }
            //两个指针i和j
            int j = 0;
            for(int i=0;i<nums.length;i++) {
                //当前元素!=0，就把其交换到左边，等于0的交换到右边
                if(nums[i]!=0) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j++] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] nums = {0,1,0,3,12};
        Solution solution = new Solution();
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

    }

    //双指针
    class Solution2 {
        public void moveZeroes(int[] nums) {
            int n = nums.length, left = 0, right = 0;
            while (right < n) {
                if (nums[right] != 0) {
                    swap(nums, left, right);
                    left++;
                }
                right++;
            }
        }

        public void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }

}
