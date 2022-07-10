package leetcode;

import utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class code_337 {

    class Solution {
        //当前节点被选中时的最高金额
        Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
        //当前节点未被选中时的最高金额
        Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

        //程序入口
        public int rob(TreeNode root) {
            dfs(root);
            return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
        }

        public void dfs(TreeNode node) {
            if (node == null) {
                return;
            }
            //程序
            dfs(node.left);
            dfs(node.right);
            f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
            g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
        }
    }

    //动态规划优化(我们只关系它的孩子节点们的f和g是多少)
    class Solution2 {
        public int rob(TreeNode root) {
            int[] rootStatus = dfs(root);
            return Math.max(rootStatus[0], rootStatus[1]);
        }

        public int[] dfs(TreeNode node) {
            //最底层，最边界的结构
            if (node == null) {
                return new int[]{0, 0};
            }
            //再继续递归，获取左侧值
            int[] l = dfs(node.left);
            int[] r = dfs(node.right);
            //上述两个迭代完成以后，根据题目要求处理结果
            int selected = node.val + l[1] + r[1];
            int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
            //数组下标0表示该节点被选择，下标1表示该节点未被选择
            return new int[]{selected, notSelected};
        }
    }

}
