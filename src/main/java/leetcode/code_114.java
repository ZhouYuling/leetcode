package leetcode;

import utils.TreeNode;

import java.util.*;

public class code_114 {

    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        preorderTraversal(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    public void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }


    public void flatten2(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        //使用队列
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        int size = list.size();
        for (int i = 1; i < size; i++) {
            //获取根节点(前序遍历根节点)
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }


    // 前序遍历和展开同时进行
    public void flatten3(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            // 在prev不为null 时、对 prev 的左右子节点进行更新。
            if (prev != null) {
                prev.left = null;
                prev.right = curr;
            }
            TreeNode left = curr.left, right = curr.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
            // 维护上一个访问的节点 prev
            prev = curr;
        }
    }

    // 用这种前序遍历不能形成链表，因为替换会删除原来右链接，丢失节点
    // 1
    // 2  5
    // 3 4  6
    // 如上，会丢失4,5,6节点，只能输出1,2,3
    // 而上面的那种先序遍历，已经把右节点存储到栈中，不会丢失节点
    public void flatten33(TreeNode root){	//先序遍历  迭代
        Stack<TreeNode> stack = new Stack<TreeNode>();		//栈
        TreeNode curr = root;
        TreeNode prev = null;
        while((curr != null) || !stack.empty()) {
            if(curr != null) {					//将左孩子压栈，也可以写成while(node != null)，那么else和后面的括号就可以不用了
                //list.add(node.val);				//访问
                if (prev != null) {
                    prev.left = null;
                    prev.right = curr;
                }
                stack.push(curr);
                prev = curr;
                curr = curr.left;
            }else {
                curr = stack.pop();
                prev = curr;
                curr = curr.right;
            }
        }
    }

    public void flatten4(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode l1 = new TreeNode(2);
        TreeNode r1 = new TreeNode(5);
        root.left = l1;
        root.right = r1;
        l1.left = new TreeNode(3);
        l1.right = new TreeNode(4);
        r1.right = new TreeNode(6);

        new code_114().flatten33(root);
        System.out.println(root);



    }

}
