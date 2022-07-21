package leetcode;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class code_145 {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(res, root);
        return res;
    }

    private void postorder(List<Integer> res, TreeNode node) {
        if (node == null) return;
        postorder(res, node.left);
        postorder(res, node.right);
        res.add(node.val);
    }

    // 双栈写法
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        Deque<TreeNode> output = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                output.push(node);
                stack.push(node);
                node = node.right;
            }
            node = stack.pop();
            node = node.left;
        }
        while (!output.isEmpty()) {
            res.add(output.pop().val);
        }
        return res;
    }

    // 单栈写法
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        TreeNode prev = null;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                if (cur.right == null || prev == cur.right) {
                    res.add(cur.val);
                    prev = cur;
                    cur = null;
                } else {
                    stack.push(cur);
                    cur = cur.right;
                }
            }
        }
        return res;
    }

}
