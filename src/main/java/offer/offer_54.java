package offer;

import utils.TreeNode;

public class offer_54 {

    class Solution {
        int res,k;
        public int kthLargest(TreeNode root, int k) {
            this.k = k;
            dfs(root);
            return res;
        }

        private void dfs(TreeNode node) {
            // 这个边界条件判断，千万别忘记了
            if(node == null) return;
            dfs(node.right);
            if(k == 0) return;
            if (--k == 0) res = node.val;
            dfs(node.left);

        }
    }

    public static void main(String[] args) {

        int k = 1;
        System.out.println(--k == 0);
        k = 1;
        System.out.println(k-- == 0);

    }

}
