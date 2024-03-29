package leetcode;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class code_257 {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        constructPaths(root, "", paths);
        return paths;
    }

    private void constructPaths(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            StringBuilder pathSB = new StringBuilder(path);
            pathSB.append(root.val);
            if (root.left == null && root.right == null)
                paths.add(pathSB.toString());
            else {
                pathSB.append("->");
                constructPaths(root.left, pathSB.toString(), paths);
                constructPaths(root.right, pathSB.toString(), paths);
            }
        }
    }

}
