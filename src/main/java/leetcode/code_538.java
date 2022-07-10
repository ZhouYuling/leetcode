package leetcode;

import utils.TreeNode;

public class code_538 {

    //反序中序遍历
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            //先找到最大值
            convertBST(root.right);
            //更新该节点的值
            sum += root.val;
            root.val = sum;
            //遍历左节点
            convertBST(root.left);
        }
        return root;
    }

}
