package utils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Utils {

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static String treeToStr(TreeNode root) {
        if(root==null) return "";
        List res=new ArrayList();
        LinkedList<TreeNode> qu=new LinkedList<>();
        qu.addFirst(root);
        while(!qu.isEmpty()){
            TreeNode node=qu.removeLast();
            if(node==null){
                res.add("null");
                continue;
            }else{
                res.add(node.val);
                qu.addFirst(node.left);
                qu.addFirst(node.right);
            }
        }
        StringBuilder r=new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            r.append(res.get(i));
            if(i<res.size()-1) r.append(",");
        }
        return r.toString();
    }

    public static TreeNode createTree(String data) {
        if(data.length()==0) return null;
        String[] req=data.split(",");
        LinkedList<TreeNode> qu=new LinkedList<>();
        TreeNode root=new TreeNode(Integer.parseInt(req[0]));
        qu.addFirst(root);
        int index=1;
        while(index<req.length){
            TreeNode node=qu.removeLast();
            if(!("null".equals(req[index]))){
                node.left=new TreeNode(Integer.parseInt(req[index]));
                qu.addFirst(node.left);
            }
            index++;
            if(!("null".equals(req[index]))){
                node.right=new TreeNode(Integer.parseInt(req[index]));
                qu.addFirst(node.right);
            }
            index++;
        }
        return root;
    }

    public static String printTree(TreeNode root) {
        if(root==null) return "";
        int depth = maxDepth(root);
        List<List<String>> res=new ArrayList<>();
        Queue<TreeNode> qu=new LinkedList<>();
        qu.offer(root);
        int flag = 1;
        while(!qu.isEmpty()){
            int size = qu.size();
            List<String> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node=qu.poll();
                if(node==null){
                    tmp.add("null");
                }else{
                    tmp.add(node.val + "");
                    if (node.left != null || flag < depth) qu.offer(node.left);
                    if (node.right != null || flag < depth) qu.offer(node.right);
                }
            }
            flag ++;
            res.add(tmp);
        }
        StringBuilder r=new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            r.append(String.join(",", res.get(i))).append("\r\n");
        }
        return r.toString();
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }


}
