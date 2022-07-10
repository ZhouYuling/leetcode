package leetcode;

import utils.ListNode;

public class code_148 {

    // 自顶向上归并排序
    class Solution {
        public ListNode sortList(ListNode head) {
            return sortList(head, null);
        }

        public ListNode sortList(ListNode head, ListNode tail) {
            //如果链表
            if (head == null) {
                return null;
            }
            if (head.next == tail) {
                head.next = null;
                return head;
            }
            //快慢指针寻找链表中点，在数组中n / 2获取下标值即可
            ListNode slow = head, fast = head;
            //注意不要空指针异常
            while (fast != tail) {
                slow = slow.next;
                fast = fast.next;
                if (fast != tail) {
                    fast = fast.next;
                }
            }
            //慢指针即使中点
            ListNode mid = slow;
            //排序链表左侧
            ListNode list1 = sortList(head, mid);
            //排序链表右侧
            ListNode list2 = sortList(mid, tail);
            return merge(list1, list2);
        }

    }

    // 自底向下归并排序
    class Solution2 {
        public ListNode sortList(ListNode head) {
            if (head == null) {
                return null;
            }
            int length = 0;
            //避免链表头节点污染
            ListNode node = head;
            //获取链表节点个数
            while (node != null) {
                length++;
                node = node.next;
            }
            //使用哨兵节点
            ListNode dummyHead = new ListNode(0, head);
            for (int subLength = 1; subLength < length; subLength <<= 1) {
                ListNode prev = dummyHead, curr = dummyHead.next;
                while (curr != null) {
                    //subLength长度的链表作为head1
                    ListNode head1 = curr;
                    for (int i = 1; i < subLength && curr.next != null; i++) {
                        curr = curr.next;
                    }
                    //截取subLength长度的链表作为head2
                    ListNode head2 = curr.next;
                    //注意head1和head2不能连接在一起
                    curr.next = null;
                    curr = head2;
                    for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                        curr = curr.next;
                    }
                    //注意要截断head2和后面的链表
                    ListNode next = null;
                    if (curr != null) {
                        next = curr.next;
                        curr.next = null;
                    }
                    //合并两个链表
                    prev.next = merge(head1, head2);
                    while (prev.next != null) {
                        prev = prev.next;
                    }
                    curr = next;
                }
            }
            return dummyHead.next;
        }

    }

    public static void main(String[] args) {

        code_148 code = new code_148();
        Solution2 s2 = code.new Solution2();
//        s2.sortList();


    }

    //合并两个链表
    private ListNode merge(ListNode head1, ListNode head2) {
        //哨兵链表头
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            //从小到大排序
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        //可以使用三目运算符
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }


}
