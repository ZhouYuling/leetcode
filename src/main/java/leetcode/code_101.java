package leetcode;

import utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class code_101 {

    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        //有一个不为空，则不是对称二叉树
        if (p == null || q == null) {
            return false;
        }
        // 对应两个节点的值一定要相等
        return p.val == q.val
                // 取root节点最左侧和root节点最右侧进行对比
                && check(p.left, q.right)
                // 取root节点最右侧和root节点最左侧进行对比
                && check(p.right, q.left);
    }

    public boolean isSymmetric2(TreeNode root) {
        return check2(root, root);
    }

    public boolean check2(TreeNode u, TreeNode v) {
        //引入队列，把递归程序写成迭代程序
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }

            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }

}
