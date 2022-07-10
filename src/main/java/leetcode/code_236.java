package leetcode;

import utils.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class code_236 {

    //递归
    static class Solution {

        private TreeNode ans;

        public Solution() {
            this.ans = null;
        }

        //root节点是否是p和q的公共祖先
        private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
            //终止条件
            if (root == null) return false;
            //遍历到最左侧
            boolean lson = dfs(root.left, p, q);
            //然后查看右节点
            boolean rson = dfs(root.right, p, q);
            if ((lson && rson) || //当前节点是p,q的公共节点
                    ((root.val == p.val || root.val == q.val) && (lson || rson))) { // xx恰好是pp节点或qq节点且它的左子树或右子树有一个包含了另一个节点的情况
                ans = root;
            }
            return lson || rson || (root.val == p.val || root.val == q.val);
        }

        //程序入口 节点p 和节点q 的最近公共祖先
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            this.dfs(root, p, q);
            return this.ans;
        }
    }

    //存储父节点
    static class Solution2 {
        //key为左右节点，value为root父亲节点
        Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
        Set<Integer> visited = new HashSet<Integer>();

        public void dfs(TreeNode root) {
            if (root.left != null) {
                parent.put(root.left.val, root);
                dfs(root.left);
            }
            if (root.right != null) {
                parent.put(root.right.val, root);
                dfs(root.right);
            }
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            //递归遍历整颗二叉树
            dfs(root);
            //遍历找到p节点的最上面父亲节点，并保存所有遍历节点
            while (p != null) {
                visited.add(p.val);
                p = parent.get(p.val);
            }
            while (q != null) {
                //查找p的所有父亲节点中，是否存在q的父亲节点
                if (visited.contains(q.val)) {
                    return q;
                }
                //获取q的父亲节点
                q = parent.get(q.val);
            }
            return null;
        }
    }

}
