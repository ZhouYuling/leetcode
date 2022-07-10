package leetcode;

import java.util.ArrayList;
import java.util.List;

public class code_078 {

    static class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> output = new ArrayList();
            int n = nums.length;

            for (int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n + 1); ++i) {
                // generate bitmask, from 0..00 to 1..11
                String bitmask = Integer.toBinaryString(i).substring(1);

                // append subset corresponding to that bitmask
                List<Integer> curr = new ArrayList();
                for (int j = 0; j < n; ++j) {
                    if (bitmask.charAt(j) == '1') curr.add(nums[j]);
                }
                output.add(curr);
            }
            return output;
        }
    }

    public static void main(String[] args) {

        System.out.println(1 << 3);
        Solution solution = new Solution();
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = solution.subsets(nums);
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }

    }

    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public void dfs(int cur, int[] nums) {
        if (cur == nums.length){
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums);
    }

    public List<List<Integer>> subsets1(int[] nums) {

        int n = nums.length;
        for (int mask = 0; mask < (1 << n); mask++) {
            t.clear();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(t));
        }

        return ans;
    }

}
