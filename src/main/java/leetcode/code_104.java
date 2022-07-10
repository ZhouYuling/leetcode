package leetcode;

import utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class code_104 {

    static class Solution {
        //广度优先搜索
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            //使用队列
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);
            //层序输出的代码
            int ans = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size > 0) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    size--;
                }
                //输出一层，所以深度+1
                ans++;
            }
            return ans;
        }
    }

    public static void main(String[] args) {



    }

    //深度优先搜索
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            //查找左节点深度
            int leftHeight = maxDepth(root.left);
            //查找右节点深度
            int rightHeight = maxDepth(root.right);
            //左节点和右节点最大值+1
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

}
