package offer;

import utils.TreeNode;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;

public class offer_55 {

    // 剑指 Offer 55 - I. 二叉树的深度
    // 深度优先遍历
    class Solution {
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            else {
                int leftMaxDepth = maxDepth(root.left);
                int rightMaxDepth = maxDepth(root.right);
                return Math.max(leftMaxDepth, rightMaxDepth) + 1;
            }
        }
    }

    class Solution2 {
        public int maxDepth(TreeNode root) {
            int ans = 0;
            if (root == null) return ans;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                while (size > 0) {
                    TreeNode node = q.poll();
                    if (node.left != null) q.offer(node.left);
                    if (node.right != null) q.offer(node.right);
                    size --;
                }
                ans ++;
            }

            return ans;
        }
    }

    // 剑指 Offer 55 - II. 平衡二叉树
    class Solution3 {
        public boolean isBalanced(TreeNode root) {
            if (root == null) return true;
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }

        private int height(TreeNode node) {
            if (node == null) return 0;
            return Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    class Solution4 {
        public boolean isBalanced(TreeNode root) {
            return height(root) >= 0;
        }

        private int height(TreeNode root) {
            if (root == null) return 0;
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) return -1;
            else return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static void main(String[] args) {
        byte[] bits = new byte[1];
        BitSet bitSet = new BitSet(10);
        


    }

}
