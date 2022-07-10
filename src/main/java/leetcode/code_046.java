package leetcode;

import java.util.*;

public class code_046 {

    // 回溯
    class Solution {
        public void backtrack(int n,
                              ArrayList<Integer> output,
                              List<List<Integer>> res,
                              int first) {
            // 所有数都填完了
            if (first == n)
                res.add(new ArrayList<Integer>(output));
            for (int i = first; i < n; i++) {
                // 动态维护数组
                Collections.swap(output, first, i);
                // 继续递归填下一个数
                backtrack(n, output, res, first + 1);
                // 撤销操作
                Collections.swap(output, first, i);
            }
        }

        //程序入口
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new LinkedList();

            ArrayList<Integer> output = new ArrayList<Integer>();
            for (int num : nums)
                output.add(num);

            int n = nums.length;
            backtrack(n, output, res, 0);
            return res;
        }
    }

    public static void main(String[] args) {

        code_046 code_046 = new code_046();
        Solution solution = code_046.new Solution();
        int[] nums = {1,2,3};
        List<List<Integer>> permute = solution.permute(nums);
        System.out.println(permute);

        String[] array = { "Hello", "World" };

        // 将数组转化为ArrayList
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));
        System.out.println(list);


    }

    // 手写回溯方法
    class Solution2 {

        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> permute(int[] nums) {
            if (nums == null || nums.length == 0) return new ArrayList<>();
            List<Integer> output = new ArrayList<>();
            for (int num : nums) output.add(num);
            backtrack(nums.length, output, 0);
            return res;
        }

        private void backtrack(int n, List<Integer> output, int first) {

            if(first == n) res.add(new ArrayList<>(output));
            // 这个for循环的起始值经常写错
            for (int i = first; i < output.size(); i++) {
                Collections.swap(output, first, i);
                backtrack(n, output, first + 1);
                Collections.swap(output, first, i);
            }


        }

    }

}
