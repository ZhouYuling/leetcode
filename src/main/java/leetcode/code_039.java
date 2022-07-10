package leetcode;

import java.util.ArrayList;
import java.util.List;

public class code_039 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        dfs(candidates, target, ans, combine, 0);
        return ans;

    }

    //我们定义递归函数 dfs(target,combine,idx) 表示当前在 candidates 数组的第 idx 位，还剩 target 要组合，已经组合的列表为 combine
    private void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length)
            return;

        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }

        // 选择跳过不用第 idx 个数，即执行dfs(target,combine,idx+1)
        dfs(candidates, target, ans, combine, idx + 1);

        // 也可以选择使用第 idx 个数，即执行 dfs(target−candidates[idx],combine,idx)，注意到每个数字可以被无限制重复选取，因此搜索的下标仍为 idx
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }

    }

}
