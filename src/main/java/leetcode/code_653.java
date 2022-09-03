package leetcode;

import utils.TreeNode;

import java.util.*;

public class code_653 {
    // 深度优先搜索 + 哈希表
    Set<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
    // 广度优先搜索+哈希表
    public boolean findTarget2(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (set.contains(k - node.val)) return true;
            set.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return false;
    }
    // 方法三：深度优先搜索 + 中序遍历 + 双指针
    List<Integer> list = new ArrayList<Integer>();
    public boolean findTarget3(TreeNode root, int k) {
        inorderTraversal(root);
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (list.get(left) + list.get(right) == k) return true;
            if (list.get(left) + list.get(right) < k) left ++;
            else right --;
        }
        return false;
    }
    private void inorderTraversal(TreeNode node) {
        if (node == null) return;
        inorderTraversal(node.left);
        list.add(node.val);
        inorderTraversal(node.right);
    }
    // 方法四：迭代 + 中序遍历 + 双指针
    public boolean findTarget4(TreeNode root, int k) {
        TreeNode left = root, right = root;
        Deque<TreeNode> leftStack = new ArrayDeque<>();
        Deque<TreeNode> rightStack = new ArrayDeque<>();
        leftStack.push(left);
        while (left.left != null) {
            leftStack.push(left.left);
            left = left.left;
        }
        rightStack.push(right);
        while (right.right != null) {
            rightStack.push(right.right);
            right = right.right;
        }
        while (left != right) {
            if (left.val + right.val == k) return true;
            if (left.val + right.val < k) left = getLeft(leftStack);
            else right = getRight(rightStack);
        }
        return false;
    }

    private TreeNode getRight(Deque<TreeNode> stack) {
        TreeNode root = stack.pop();
        TreeNode node = root.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        return root;
    }

    private TreeNode getLeft(Deque<TreeNode> stack) {
        TreeNode root = stack.pop();
        TreeNode node = root.left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
        return root;
    }

}
