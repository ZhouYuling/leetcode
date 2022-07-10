package learn;

import utils.TreeNode;

import java.util.*;

public class BinaryTree {

    List<Integer> preList = new ArrayList<Integer>();

    public List<Integer> preOrder(TreeNode root) {		//先序遍历  递归
        if(root == null) {
            return null;
        }
        preList.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
        return preList;
    }

    List<Integer> inList = new ArrayList<Integer>();
    public List<Integer> inOrder(TreeNode root){		//中序遍历   递归
        if(root == null)
            return null;
        inOrder(root.left);
        inList.add(root.val);
        inOrder(root.right);
        return inList;
    }

    List<Integer> postList = new ArrayList<Integer>();
    public List<Integer> postOrder(TreeNode root){		//后序遍历   递归
        if(root == null)
            return null;
        postOrder(root.left);
        postOrder(root.right);
        postList.add(root.val);
        return postList;
    }

    public List<Integer> preOrderStack(TreeNode root){	//先序遍历  迭代
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();		//栈
        TreeNode node = root;
        while((node != null) || !stack.empty()) {
            if(node != null) {					//将左孩子压栈
                list.add(node.val);				//访问
                stack.push(node);
                node = node.left;
            }else {
                node = stack.pop();
                node = node.right;
            }
        }
        return list;
    }

    public List<Integer> inOrderStack(TreeNode root){		//中序遍历  迭代
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();		//栈
        TreeNode node = root;
        while((node != null) || !stack.empty()) {
            if(node != null) {					//将左孩子压栈
                stack.push(node);
                node = node.left;
            }else {
                node = stack.pop();			//出栈并访问
                list.add(node.val);
                node = node.right;
            }
        }
        return list;
    }

    public List<Integer> postOrderStack(TreeNode root){		//后序遍历  迭代
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();		//栈
        Stack<TreeNode> output = new Stack<TreeNode>();		//构造一个中间栈，存储逆后序遍历的结果
        TreeNode node = root;
        while((node != null) || !stack.empty()) {
            if(node != null) {					//将左孩子压栈
                output.push(node);			//保存节点，相当于前序遍历,根-右-左
                stack.push(node);
                node = node.right;
            }else {
                node = stack.pop();
                node = node.left;
            }
        }
        while(output.size() > 0) {
            node = output.pop();
            list.add(node.val);
        }
        return list;
    }

    List<Integer> ans = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;  // 用于记录上一次访问的节点
        while(cur!=null || !s.isEmpty()) {
            while(cur!=null) {
                s.push(cur);
                cur = cur.left;
            }
            if(!s.isEmpty()) {
                cur = s.pop();
                if(cur.right==null || pre==cur.right) { // 访问节点的条件
                    ans.add(cur.val); // 访问
                    pre = cur; // 这一步是记录上一次访问的节点
                    cur = null; // 此处为了跳过下一次循环的访问左子节点的过程，直接进入栈的弹出阶段，因为但凡在栈中的节点，它们的左子节点都肯定被经过且已放入栈中。
                }
                else { // 不访问节点的条件
                    s.push(cur); // 将已弹出的根节点放回栈中
                    cur = cur.right; // 经过右子节点
                }
            }
        }
        return ans;
    }


    List<Integer> preorder = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            preorder.add(curr.val);
            TreeNode left = curr.left, right = curr.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
        }
        return preorder;
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = binaryTree.init();
        List<Integer> postList = binaryTree.postOrderStack(root);	//后序遍历  迭代
        System.out.println(postList);		//结果：[4, 5, 2, 6, 7, 3, 1]

        List<Integer> integers = binaryTree.preorderTraversal(root);
        System.out.println(integers);
    }

    /**
     *    1
     *  2   3
     * 4 5 6 7
     */
    public TreeNode init() {			//测试数据
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node3 = new TreeNode(3, node6, node7);
        TreeNode node1 = new TreeNode(1, node2, node3);
        return node1;
    }


}
