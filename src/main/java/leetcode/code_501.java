package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class code_501 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    static class Solution {
        int base, count, maxCount;
        List<Integer> answer = new ArrayList<Integer>();

        public int[] findMode(TreeNode root) {
            TreeNode cur = root, pre = null;
            while (cur != null) {
                if (cur.left == null) {
                    update(cur.val);
                    cur = cur.right;
                    continue;
                }
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    update(cur.val);
                    cur = cur.right;
                }
            }
            int[] mode = new int[answer.size()];
            for (int i = 0; i < answer.size(); ++i) {
                mode[i] = answer.get(i);
            }
            return mode;
        }

        public void update(int x) {
            if (x == base) {
                ++count;
            } else {
                count = 1;
                base = x;
            }
            if (count == maxCount) {
                answer.add(base);
            }
            if (count > maxCount) {
                maxCount = count;
                answer.clear();
                answer.add(base);
            }
        }
    }

    public static void main(String[] args) {

        TreeNode treeNode = new TreeNode(1);
        treeNode.left = null;
        treeNode.right = new TreeNode(2);
        treeNode.right.right = new TreeNode(2);

        Solution solution = new Solution();
        int[] mode = solution.findMode(treeNode);
        System.out.println(Arrays.toString(mode));

    }


}
