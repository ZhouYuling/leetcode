package leetcode;

import utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class code_437 {

    //前缀和：定义由根结点到当前结点的路径上所有节点的和
    class Solution {
        //程序入口
        public int pathSum(TreeNode root, int targetSum) {
            Map<Long, Integer> prefix = new HashMap<Long, Integer>();
            prefix.put(0L, 1);
            return dfs(root, prefix, 0, targetSum);
        }

        //深度搜索遍历树
        public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
            if (root == null) {
                return 0;
            }

            int ret = 0;
            //前缀和
            curr += root.val;

            //二叉树
            ret = prefix.getOrDefault(curr - targetSum, 0);
            prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
            ret += dfs(root.left, prefix, curr, targetSum);
            ret += dfs(root.right, prefix, curr, targetSum);
            //还原现场
            prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

            return ret;
        }
    }

    //深度优先遍历
    class Solution2 {
        public int pathSum(TreeNode root, int targetSum) {

            //边界
            if (root == null) {
                return 0;
            }

            //求当前节点路径的数量和
            int ret = rootSum(root, targetSum);
            //求左节点路径的数量和
            ret += pathSum(root.left, targetSum);
            //求右节点路径的数量和
            ret += pathSum(root.right, targetSum);
            return ret;
        }

        //该二叉树里节点值之和等于targetSum 的 路径 的数目
        public int rootSum(TreeNode root, int targetSum) {
            int ret = 0;

            if (root == null) {
                return 0;
            }
            int val = root.val;
            if (val == targetSum) {
                ret++;
            }

            // 递归目标值为 targetSum - val，所以不会有重复
            ret += rootSum(root.left, targetSum - val);
            ret += rootSum(root.right, targetSum - val);
            return ret;
        }
    }


}
