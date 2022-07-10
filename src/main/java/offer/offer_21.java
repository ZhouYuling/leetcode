package offer;

import utils.Utils;

import javax.rmi.CORBA.Util;
import java.util.Arrays;

public class offer_21 {

    static class Solution {
        public int[] exchange(int[] nums) {
            if (nums.length == 0) return nums;
            int left = 0, right = nums.length - 1;
            while (left < right) {
                while (left < right && nums[left] % 2 == 1) left ++;
                while (left < right && nums[right] % 2 == 0) right --;
                Utils.swap(nums, left, right);
            }

            return nums;
        }


    }


    public static void main(String[] args) {

        int[] exchange = new Solution().exchange(new int[]{1, 3, 5});
        System.out.println(Arrays.toString(exchange));

    }

}
