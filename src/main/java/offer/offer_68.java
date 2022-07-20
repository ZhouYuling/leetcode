package offer;

import jdk.nashorn.internal.ir.WhileNode;
import utils.TreeNode;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class offer_68 {

    // 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
    // 一次遍历
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode ancestor = root;
            while (true) {
                if (p.val < ancestor.val && q.val < ancestor.val) ancestor = ancestor.left;
                else if (p.val > ancestor.val && q.val > ancestor.val) ancestor = ancestor.right;
                else break;
            }

            return ancestor;
        }
    }

    class Solution2 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode ancestor = null;
            List<TreeNode> p_list = getPath(root, p);
            List<TreeNode> q_list = getPath(root, q);

            for (int i = 0; i < p_list.size() && i < q_list.size(); i++) {
                if (p_list.get(i).val == q_list.get(i).val) {
                    ancestor = p_list.get(i);
                } else break;
            }

            return ancestor;
        }

        private List<TreeNode> getPath(TreeNode root, TreeNode target) {
            List<TreeNode> path = new ArrayList<>();
            TreeNode node = root;
            while (node != null && node.val != target.val) {
                path.add(node);
                if (node.val > target.val) node = node.left;
                else node = node.right;
            }
            path.add(node);

            return path;
        }
    }

    // 剑指 Offer 68 - II. 二叉树的最近公共祖先
    //
    class Solution3 {

        Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
        List<Integer> visited = new ArrayList<>();

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            dfs(root);
            while (p != null) {
                visited.add(p.val);
                p = parent.get(p.val);
            }
            while (q != null) {
                if (visited.contains(q.val)) return q;
                q = parent.get(q.val);
            }

            return null;
        }

        private void dfs(TreeNode root) {
            if (root.left != null) {
                parent.put(root.left.val, root);
                dfs(root.left);
            }
            if (root.right != null) {
                parent.put(root.right.val, root);
                dfs(root.right);
            }
        }
    }

    class Solution4 {


        // https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/solution/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/
        // Krahets大佬的解法
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root.val == p.val || root.val == q.val) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left == null && right == null) return null;
            if (left == null) return right;
            if (right == null) return left;
            return root;
        }

    }

    public static void main(String[] args) {

        TreeNode tree = Utils.createTree("6,2,8,0,4,7,9,null,null,3,5");
        offer_68 code = new offer_68();
        Solution2 solution2 = code.new Solution2();
        TreeNode node = solution2.lowestCommonAncestor(tree, new TreeNode(2), new TreeNode(8));
        System.out.println(node.val);


    }

}
