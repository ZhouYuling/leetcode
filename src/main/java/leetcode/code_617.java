package leetcode;

import utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class code_617 {

    static class Solution {
        //广度优先搜索
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if (t1 == null) {
                return t2;
            }
            if (t2 == null) {
                return t1;
            }
            TreeNode merged = new TreeNode(t1.val + t2.val);
            //使用三个独立的队列进行合并操作
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
            Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
            //插入队列
            queue.offer(merged);
            queue1.offer(t1);
            queue2.offer(t2);
            while (!queue1.isEmpty() && !queue2.isEmpty()) {
                //node用于存储临时节点，弹出队列
                TreeNode node = queue.poll(), node1 = queue1.poll(), node2 = queue2.poll();
                TreeNode left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
                //处理左边的节点
                if (left1 != null || left2 != null) {
                    if (left1 != null && left2 != null) {
                        TreeNode left = new TreeNode(left1.val + left2.val);
                        node.left = left;
                        //左节点压入栈
                        queue.offer(left);
                        queue1.offer(left1);
                        queue2.offer(left2);
                    } else if (left1 != null) {
                        node.left = left1;
                    } else if (left2 != null) {
                        node.left = left2;
                    }
                }
                //处理右边的节点
                if (right1 != null || right2 != null) {
                    if (right1 != null && right2 != null) {
                        TreeNode right = new TreeNode(right1.val + right2.val);
                        node.right = right;
                        queue.offer(right);
                        queue1.offer(right1);
                        queue2.offer(right2);
                    } else if (right1 != null) {
                        node.right = right1;
                    } else {
                        node.right = right2;
                    }
                }
            }
            return merged;
        }
    }


    //深度优先搜索
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        //另外一个节点为空，其节点和子节点直接返回就好了，引用直接关联上了
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees(t1.left, t2.left);
        merged.right = mergeTrees(t1.right, t2.right);
        return merged;

    }

}
