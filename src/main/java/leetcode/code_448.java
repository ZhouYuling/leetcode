package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class code_448 {

    static class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {

            // Hash table for keeping track of the numbers in the array
            // Note that we can also use a set here since we are not
            // really concerned with the frequency of numbers.
            HashMap<Integer, Boolean> hashTable = new HashMap<Integer, Boolean>();

            // Add each of the numbers to the hash table
            for (int i = 0; i < nums.length; i++) {
                hashTable.put(nums[i], true);
            }

            // Response array that would contain the missing numbers
            List<Integer> result = new LinkedList<Integer>();

            // Iterate over the numbers from 1 to N and add all those
            // that don't appear in the hash table.
            for (int i = 1; i <= nums.length; i++) {
                if (!hashTable.containsKey(i)) {
                    result.add(i);
                }
            }

            return result;
        }
    }

    static class Solution2 {
        public List<Integer> findDisappearedNumbers(int[] nums) {

            // Iterate over each of the elements in the original array
            for (int i = 0; i < nums.length; i++) {

                // Treat the value as the new index
                int newIndex = Math.abs(nums[i]) - 1;

                // Check the magnitude of value at this new index
                // If the magnitude is positive, make it negative
                // thus indicating that the number nums[i] has
                // appeared or has been visited.
                if (nums[newIndex] > 0) {
                    nums[newIndex] *= -1;
                }
            }

            // Response array that would contain the missing numbers
            List<Integer> result = new LinkedList<Integer>();

            // Iterate over the numbers from 1 to N and add all those
            // that have positive magnitude in the array
            for (int i = 1; i <= nums.length; i++) {

                if (nums[i - 1] > 0) {
                    result.add(i);
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = {1, 1, 4, 4, 5};
        List<Integer> disappearedNumbers = solution.findDisappearedNumbers(nums);
        System.out.println(disappearedNumbers);

        Solution2 solution2 = new Solution2();
        List<Integer> disappearedNumbers1 = solution2.findDisappearedNumbers(nums);
        System.out.println(disappearedNumbers1);


    }


    class Solution3 {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            int n = nums.length;
            for (int num : nums) {
                //nums充当哈希表
                int x = (num - 1) % n;
                //注意是+n
                nums[x] += n;
            }
            List<Integer> ret = new ArrayList<Integer>();
            //不超过n的数
            for (int i = 0; i < n; i++) {
                if (nums[i] <= n) {
                    ret.add(i + 1);
                }
            }
            return ret;
        }
    }


}
