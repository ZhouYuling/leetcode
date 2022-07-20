package offer;

import utils.TreeNode;
import utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class offer_07 {

    class Solution {

        private Map<Integer, Integer> map;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || inorder == null || preorder.length != inorder.length) return null;

            int n = preorder.length;
            map = new HashMap<Integer, Integer>();
            for (int i = 0; i < n; i++) {
                map.put(inorder[i], i);
            }

            return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
        }

        private TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
            if (preorder_left > preorder_right) return null;
            int preorder_root = preorder_left;
            int inorder_root = map.get(preorder[preorder_root]);
            TreeNode root = new TreeNode(preorder[preorder_root]);
            int size_left_subtree = inorder_root - inorder_left;
            root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left +size_left_subtree, inorder_left, inorder_root - 1);
            root.right = myBuildTree(preorder, inorder, preorder_left +size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
            return root;
        }
    }

    public static void main(String[] args) {
        offer_07 code = new offer_07();
        Solution solution = code.new Solution();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode node = solution.buildTree(preorder, inorder);
        System.out.println(Utils.printTree(node));
    }

}
