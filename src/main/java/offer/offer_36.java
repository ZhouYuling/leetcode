package offer;

import utils.TreeNode;
import utils.Utils;

import java.util.HashMap;

public class offer_36 {

    class Solution {
        TreeNode pre, head;
        public TreeNode treeToDoublyList(TreeNode root) {
            if(root == null) return null;
            dfs(root);
            head.left = pre;
            pre.right = head;//进行头节点和尾节点的相互指向，这两句的顺序也是可以颠倒的
            return head;
        }
        void dfs(TreeNode cur) {
            if(cur == null) return;
            dfs(cur.left);
            //pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,当pre==null时，cur左侧没有节点,即此时cur为双向链表中的头节点
            if(pre==null) head = cur;
            //反之，pre!=null时，cur左侧存在节点pre，需要进行pre.right=cur的操作。
            else pre.right = cur;

            cur.left = pre;//pre是否为null对这句没有影响,且这句放在上面两句if else之前也是可以的。

            pre = cur;//pre指向当前的cur
            dfs(cur.right);//全部迭代完成后，pre指向双向链表中的尾节点
        }
    }

    public static void main(String[] args) {

        TreeNode tree = Utils.createTree("4,2,5,1,3");
        String s = Utils.printTree(tree);
        System.out.println(s);

        offer_36 offer = new offer_36();
        Solution solution = offer.new Solution();
        solution.treeToDoublyList(tree);

    }

}
