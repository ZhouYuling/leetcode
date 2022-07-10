package leetcode;

import java.util.ArrayList;
import java.util.List;

public class code_077 {

    // 回溯
    static class Solution {

        List<Integer> tmp = new ArrayList<Integer>();
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {
            dfs(1, n, k);
            return res;
        }

        private void dfs(int curr, int n, int k) {
            // 剪枝
            if (tmp.size() + (n - curr + 1) < k) return;
            // 终止条件
            if (tmp.size() == k) {
                // 这个地方还不能直接添加tmp，只能通过下面这种方式，奇怪了
                res.add(new ArrayList<Integer>(tmp));
                return;
            }
            tmp.add(curr);
            dfs(curr + 1, n, k);
            tmp.remove(tmp.size() - 1);
            dfs(curr +1, n, k);


        }


    }

}
