package leetcode;

import utils.TreeNode;

public class code_700 {

    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (val == root.val) return root;
            root = val < root.val ? root.left : root.right;
        }
        return null;
    }

    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        return val < root.val ? searchBST(root.left, val) : searchBST2(root.right, val);
    }


}
