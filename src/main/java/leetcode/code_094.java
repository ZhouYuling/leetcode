package leetcode;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class code_094 {

    public List<Integer> inorderTraversal(TreeNode root) {
        //根据题目要求，定义链表答案
        List<Integer> res = new ArrayList<Integer>();
        //定义方法中序遍历
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        //左
        inorder(root.left, res);
        //中
        res.add(root.val);
        //右
        inorder(root.right, res);
    }


    public List<Integer> inorderTraversal2(TreeNode root) {
        //保存结果
        List<Integer> res = new ArrayList<Integer>();
        //通过栈结构进行遍历
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            //循环直到最左节点
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            //弹出栈节点，用于从子节点跳转到夫节点
            root = stk.pop();
            res.add(root.val);
            //遍历右节点
            root = root.right;
        }
        return res;
    }


    // Morris中序遍历
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            TreeNode predecessor = null;

            while (root != null) {
                if (root.left != null) {
                    // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                    predecessor = root.left;
                    while (predecessor.right != null && predecessor.right != root) {
                        predecessor = predecessor.right;
                    }

                    // 让 predecessor 的右指针指向 root，继续遍历左子树
                    if (predecessor.right == null) {
                        predecessor.right = root;
                        root = root.left;
                    }
                    // 说明左子树已经访问完了，我们需要断开链接
                    else {
                        res.add(root.val);
                        predecessor.right = null;
                        root = root.right;
                    }
                }
                // 如果没有左孩子，则直接访问右孩子
                else {
                    res.add(root.val);
                    root = root.right;
                }
            }
            return res;
        }
    }

    // 手写迭代
    public List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.add(node.val);
            node = node.right;
        }

        return res;
    }

    // 手写递归
    public List<Integer> inorderTraversal5(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder2(root, res);
        return res;
    }

    private void inorder2(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder2(root.left, res);
        res.add(root.val);
        inorder2(root.right, res);
    }


}
