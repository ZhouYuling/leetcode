package offer;

import utils.ListNode;

public class offer_18 {

    static class Solution {
        // 通过改变指向删除节点
        public ListNode deleteNode(ListNode head, int val) {
            // 空链表
            if (head == null) { return null; }
            // 至少有一个节点
            boolean find = false;   // false表示没找到val，true表示找到了
            // 新增头节点
            ListNode phead = new ListNode(-1);
            phead.next = head;
            // slow指向要删除的节点的前驱，quick指向要删除的节点
            ListNode slow = phead, quick = phead.next;
            while (quick != null) {
                if (quick.val == val) {     // 找到了
                    find = true;
                    break;
                }
                slow = slow.next;
                quick = quick.next;
            }
            // 如果没找到就不用删，返回原链表
            if (!find) { return head; }
            // 找到了要删，改变前驱指向即可
            slow.next = quick.next;
            return phead.next;

        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();


    }

}
