package leetcode;

import utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class code_226 {

    //递归
    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            //遍历到最左节点
            TreeNode left = invertTree(root.left);
            //遍历到最右节点
            TreeNode right = invertTree(root.right);
            //该节点的左节点改成右节点
            root.left = right;
            //该节点的右节点改成左节点
            root.right = left;
            return root;
        }
    }

    /**
     * 层序遍历方式反转
     */
    public TreeNode invertTreeByQueue(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }

    /**
     * 深度优先遍历的方式反转
     */
    private TreeNode invertTreeByStack(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return root;
    }


}
