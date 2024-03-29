package leetcode;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class code_102 {

    //广度遍历
    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> ret = new ArrayList<List<Integer>>();
            if (root == null) {
                return ret;
            }

            //维护一个队列
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            //根节点压栈
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<Integer>();
                //判断该层有几个节点
                int currentLevelSize = queue.size();
                for (int i = 1; i <= currentLevelSize; ++i) {
                    //队头弹出一个元素
                    TreeNode node = queue.poll();
                    level.add(node.val);
                    //把该节点的左节点压入队尾
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    //把该节点的左节点压入队头
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                //遍历完一层
                ret.add(level);
            }

            return ret;
        }
    }


    //深度遍历
    class Solution2 {
        List<List<Integer>> list = new ArrayList<>();

        //程序入口
        public List<List<Integer>> levelOrder(TreeNode root) {
            if(root == null) return list;

            dfs(root, 0);
            return list;
        }

        public void dfs(TreeNode root, int level) {
            if(list.size() == level) list.add(new ArrayList<>());

            list.get(level).add(root.val);

            //层级+1
            if(root.left != null) dfs(root.left, level + 1);
            if(root.right != null) dfs(root.right, level + 1);

        }
    }

    // 手写广度遍历
    class Solution3 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<>();
                int currentLevelSize = queue.size();
                for (int i = 0; i < currentLevelSize; i++) {
                    TreeNode node = queue.poll();
                    if (node != null) {
                        level.add(node.val);
                        if (node.left != null) queue.offer(node.left);
                        if (node.right != null) queue.offer(node.right);
                    }
                }
                res.add(level);
            }

            return res;
        }
    }

    class Solution4 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;
            dfs(res, root, 0);
            return res;
        }

        private void dfs(List<List<Integer>> res, TreeNode node, int level) {
            if (res.size() == level) res.add(new ArrayList<>());
            res.get(level).add(node.val);
            if (node.left != null) dfs(res, node.left, level + 1);
            if (node.right != null) dfs(res, node.right, level + 1);
        }
    }


}
