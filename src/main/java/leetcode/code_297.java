package leetcode;

import utils.TreeNode;

import java.util.*;

public class code_297 {

    // 深度优先搜索(前序遍历)
    public static class Codec {

        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }

        // str为初始字符串
        String rserialize(TreeNode root, String str) {
            //为空，则改为None节点
            if (root == null) {
                str += "None,";
            } else {
                //使用逗号隔开各个节点
                str += String.valueOf(root.val) + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        //===================

        public TreeNode deserialize(String data) {
            //使用逗号切分序列化的字符串
            String[] data_array = data.split(",");
            List<String> data_list = new LinkedList<>(Arrays.asList(data_array));
            return rdeserialize(data_list);
        }

        TreeNode rdeserialize(List<String> l) {
            if (l.get(0).equals("None")) {
                //该节点转化以后，移除节点
                l.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
            //移除该节点
            l.remove(0);
            root.left = rdeserialize(l);
            // List字符串传递，已经处理完前节点
            root.right = rdeserialize(l);

            return root;
        }

    }

    public class Codec2 {
        public String serialize(TreeNode root) {
            if (root == null) {
                return "X";
            }
            String left = "(" + serialize(root.left) + ")";
            String right = "(" + serialize(root.right) + ")";
            return left + root.val + right;
        }

        public TreeNode deserialize(String data) {
            int[] ptr = {0};
            return parse(data, ptr);
        }

        public TreeNode parse(String data, int[] ptr) {
            if (data.charAt(ptr[0]) == 'X') {
                ++ptr[0];
                return null;
            }
            TreeNode cur = new TreeNode(0);
            cur.left = parseSubtree(data, ptr);
            cur.val = parseInt(data, ptr);
            cur.right = parseSubtree(data, ptr);
            return cur;
        }

        public TreeNode parseSubtree(String data, int[] ptr) {
            ++ptr[0]; // 跳过左括号
            TreeNode subtree = parse(data, ptr);
            ++ptr[0]; // 跳过右括号
            return subtree;
        }

        public int parseInt(String data, int[] ptr) {
            int x = 0, sgn = 1;
            if (!Character.isDigit(data.charAt(ptr[0]))) {
                sgn = -1;
                ++ptr[0];
            }
            while (Character.isDigit(data.charAt(ptr[0]))) {
                x = x * 10 + data.charAt(ptr[0]++) - '0';
            }
            return x * sgn;
        }
    }

    //BFS 网友给的广度遍历
    public class Codec3 {

        public String serialize(TreeNode root) {
            if(root == null){
                return "";
            }
            StringBuilder res = new StringBuilder();
            // 起始字符串
            res.append("[");
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()){
                TreeNode node = queue.poll();
                if(node != null){
                    res.append("").append(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }else{
                    res.append("null");
                }
                res.append(",");
            }
            // 终止字符串
            res.append("]");
            return res.toString();
        }

        public TreeNode deserialize(String data) {
            if(Objects.equals(data, "")){
                return null;
            }
            // 去掉[和]
            String[] dataList = data.substring(1, data.length() - 1).split(",");
            TreeNode root = new TreeNode(Integer.parseInt(dataList[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int i = 1;
            while(!queue.isEmpty()){
                TreeNode node = queue.poll();
                if(!"null".equals(dataList[i])){
                    node.left = new TreeNode(Integer.parseInt(dataList[i]));
                    queue.offer(node.left);
                }
                i++;
                if(!"null".equals(dataList[i])){
                    node.right = new TreeNode(Integer.parseInt(dataList[i]));
                    queue.offer(node.right);
                }
                i++;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        code_297 codec3 = new code_297();
        Codec3 codec31 = codec3.new Codec3();
        TreeNode deserialize = codec31.deserialize("[5,4,8,11,null,13,4,7,2,null,null,5,1]");
        System.out.println();
    }



}
