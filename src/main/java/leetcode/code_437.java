package leetcode;

import java.util.HashMap;
import java.util.Map;

public class code_437 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public class Solution {
        public int handle(TreeNode root, int pathSum, int target, Map<Integer, Integer> cusums) {
            if (root == null) {
                return 0;
            }
            pathSum += root.val;
            int ano = pathSum - target;
            int ans = cusums.getOrDefault(ano, 0);

            Integer sumCnt = cusums.getOrDefault(pathSum, 0);

            cusums.put(pathSum, sumCnt + 1);

            ans += handle(root.left, pathSum, target, cusums);
            ans += handle(root.right, pathSum, target, cusums);

            if (sumCnt == 0) {
                cusums.remove(pathSum);
            } else {
                cusums.put(pathSum, sumCnt);
            }
            return ans;
        }

        public int pathSum(TreeNode root, int sum) {
            Map<Integer, Integer> map = new HashMap<>();

            map.put(0, 1);

            return handle(root, 0, sum, map);
        }
    }

}
