package leetcode;

import utils.ListNode;

import java.util.ArrayList;
import java.util.List;

public class code_143 {

    class Solution {
        public void reorderList(ListNode head) {
            if (head == null) {
                return;
            }
            List<ListNode> list = new ArrayList<ListNode>();
            ListNode node = head;
            while (node != null) {
                list.add(node);
                node = node.next;
            }
            int i = 0, j = list.size() - 1;
            while (i < j) {
                list.get(i).next = list.get(j);
                i++;
                if (i == j) {
                    break;
                }
                list.get(j).next = list.get(i);
                j--;
            }
            list.get(i).next = null;
        }
    }

    public static void main(String[] args) {



    }


}
