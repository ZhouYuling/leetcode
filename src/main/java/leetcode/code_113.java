package leetcode;

import utils.TreeNode;
import utils.Utils;

import java.util.*;

public class code_113 {

    // 深度遍历
    // 只能使用回溯方法求解
    class Solution {
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;

            Deque<Integer> path = new LinkedList<>();
            dfs(root, targetSum, path, res);
            return res;
        }

        private void dfs(TreeNode node, int sum, Deque<Integer> path, List<List<Integer>> res) {
            if (node == null) return;
            if (node.val == sum && node.left == null && node.right == null) {
                path.addLast(node.val);
                res.add(new ArrayList<>(path));
                path.removeLast();
                return;
            }

            path.addLast(node.val);
            dfs(node.left, sum - node.val, path, res);
            dfs(node.right, sum - node.val, path, res);
            path.removeLast();

        }
    }

    // 广度遍历
    class Solution2 {

        Map<TreeNode, TreeNode> parentMap = new HashMap<TreeNode, TreeNode>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            if (root == null) return new ArrayList<>();

            Queue<TreeNode> queue = new LinkedList<>();
            Queue<Integer> sumQueue = new LinkedList<>();
            queue.offer(root);
            sumQueue.offer(0);
            while (!queue.isEmpty() && !sumQueue.isEmpty()) {
                TreeNode node = queue.poll();
                int recent = sumQueue.poll() + node.val;
                if (node.left == null && node.right == null) {
                    if (recent == targetSum) getPath(node);
                } else {
                    if (node.left != null) {
                        parentMap.put(node.left, node);
                        queue.offer(node.left);
                        sumQueue.offer(recent);
                    }
                    if (node.right != null) {
                        parentMap.put(node.right, node);
                        queue.offer(node.right);
                        sumQueue.offer(recent);
                    }
                }
            }
            return res;
        }

        private void getPath(TreeNode node) {
            List<Integer> resList = new LinkedList<>();
            while (node != null) {
                resList.add(node.val);
                node = parentMap.get(node);
            }
            Collections.reverse(resList);
            res.add(resList);
        }
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(5);
        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(8);
        TreeNode left2 = new TreeNode(11);
        TreeNode left3 = new TreeNode(13);
        TreeNode right3 = new TreeNode(4);
        TreeNode left4 = new TreeNode(7);
        TreeNode right4 = new TreeNode(2);
        TreeNode left5 = new TreeNode(5);
        TreeNode left6 = new TreeNode(1);

        node.left = left1;
        node.right = right1;
        left1.left = left2;
        left2.left = left4;
        left2.right = right4;
        right1.left = left3;
        right1.right = right3;
        right3.left = left5;
        right3.right = left6;

        String serialize = Utils.treeToStr(node);
        System.out.println(serialize);
        TreeNode deserialize = Utils.createTree("5,4,8,11,null,13,4,7,2,null,null,5,1");
        System.out.println(deserialize);
        System.out.println(Utils.printTree(deserialize));


        code_113 code = new code_113();
        Solution solution = code.new Solution();
        List<List<Integer>> lists = solution.pathSum(node, 22);
        System.out.println(lists);

        Solution2 solution2 = code.new Solution2();
        List<List<Integer>> lists2 = solution2.pathSum(node, 22);
        System.out.println(lists2);


    }

}
