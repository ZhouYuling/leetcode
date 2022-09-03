package leetcode;

import utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class code_098 {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // lower表示最小值 upper表示最大值
    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        //如果该节点小于最小值，或者大于最大值，肯定是错误的
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        //左子树上的所有节点均小于它的根节点的值
        return isValidBST(node.left, lower, node.val) &&
                //右子树上的所有节点均大于它的根节点
                isValidBST(node.right, node.val, upper);
    }

    // 由于是二叉搜索树，所以左侧所有节点的值小于当前节点值，右侧所有节点的值大于当前节点值
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    // 手写递归
    public boolean isValidBST3(TreeNode root) {
        return isValidBST3(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST3(TreeNode node, long lower, long upper) {
        if (node == null) return true;
        if (node.val <= lower || node.val >= upper) return false;
        return isValidBST3(node.left, lower, node.val) && isValidBST3(node.right, node.val, upper);
    }

    // 手写中序遍历
    public boolean isValidBST4(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        double inorder = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

}
