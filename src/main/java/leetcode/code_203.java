package leetcode;

import utils.ListNode;

public class code_203 {

    // 迭代
    class Solution {
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode tmp = dummy;
            // 这个地方注意，剔除的是tmp.next，所以判断是否为空要使用tmp.next，不然会报错空指针异常
            while (tmp.next != null) {
                if (tmp.next.val == val) tmp.next = tmp.next.next;
                else tmp = tmp.next;
            }

            return dummy.next;
        }
    }

    // 递归
    class Solution2 {
        public ListNode removeElements(ListNode head, int val) {
            if (head == null) return head;
            head.next = removeElements(head.next, val);
            return head.val == val ? head.next : head;
        }
    }

}
