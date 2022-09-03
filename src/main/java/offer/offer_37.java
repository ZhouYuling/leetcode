package offer;

import utils.TreeNode;
import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class offer_37 {

    public class Codec {
        public String serialize(TreeNode root) {
            if (root == null) return "null";
            return root.val + "," + serialize(root.left) + "," + serialize(root.right);
        }
        public TreeNode deserialize(String data) {
            if (data == null) return null;
            String[] arr = data.split(",");
            List<String> r = new ArrayList<>(Arrays.asList(arr));
            return dfsdeserialize(r);
        }

        private TreeNode dfsdeserialize(List<String> r) {
            if ("null".equals(r.get(0))) {
                r.remove(0);
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(r.get(0)));
            r.remove(0);
            node.left = dfsdeserialize(r);
            node.right = dfsdeserialize(r);
            return node;
        }
    }

    public class Codec2 {
        public String serialize(TreeNode root) {
            if(root==null) return "";
            ArrayList res=new ArrayList();
            LinkedList<TreeNode> qu=new LinkedList<>();
            qu.addFirst(root);
            StringBuilder r=new StringBuilder();
            while(!qu.isEmpty()){
                TreeNode node=qu.removeLast();
                if(node==null){
                    r.append(",").append("null");
                    continue;
                }else{
                    r.append(",").append(node.val);
                    qu.addFirst(node.left);
                    qu.addFirst(node.right);
                }
            }
            return r.substring(1).toString();
        }

        public TreeNode deserialize(String data) {
            if(data.length()==0) return null;
            String[] req=data.split(",");
            LinkedList<TreeNode> qu=new LinkedList<>();
            TreeNode root=new TreeNode(Integer.valueOf(req[0]));
            qu.addFirst(root);
            int index=1;
            while(index<req.length){
                TreeNode node=qu.removeLast();
                if(!("null".equals(req[index]))){
                    node.left=new TreeNode(Integer.valueOf(req[index]));
                    qu.addFirst(node.left);
                }
                index++;
                if(!("null".equals(req[index]))){
                    node.right=new TreeNode(Integer.valueOf(req[index]));
                    qu.addFirst(node.right);
                }
                index++;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        offer_37 code = new offer_37();
        Codec2 codec2 = code.new Codec2();
        TreeNode deserialize = codec2.deserialize("1,2,3,null,null,4,5");
        System.out.println(Utils.printTree(deserialize));

        TreeNode tree = Utils.createTree("1,2,3,null,null,4,5");
        String serialize = codec2.serialize(tree);
        System.out.println(serialize);

        TreeNode deserialize1 = codec2.deserialize(serialize);
        System.out.println(Utils.printTree(deserialize1));

    }


}
