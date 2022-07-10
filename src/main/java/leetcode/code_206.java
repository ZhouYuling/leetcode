package leetcode;

import utils.ListNode;

public class code_206 {

    //迭代
    static class Solution {
        public ListNode reverseList(ListNode head) {
            //前驱节点
            ListNode prev = null;
            //当前节点
            ListNode curr = head;
            while (curr != null) {
                //临时节点储存当前head后继节点
                ListNode nextTemp = curr.next;
                //反转方向，当前节点指向null
                curr.next = prev;
                //当前节点变为后继节点
                prev = curr;
                //后继节点变为前驱节点
                curr = nextTemp;
            }
            return prev;
        }
    }

    //递归
    class Solution2 {
        public ListNode reverseList(ListNode head) {
            //边界值
            if (head == null || head.next == null) {
                return head;
            }
            //递归下一个节点，相当于直接遍历到最后
            ListNode newHead = reverseList(head.next);
            //n1->n2->...->nk->nk+1<-...<-nm
            //nk的下一个节点nk+1要指向nk,则如下
            head.next.next = head;
            //同时nk的下一个节点置为空，避免循环应用
            head.next = null;
            return newHead;
        }
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);

        Solution solution = new Solution();
        ListNode listNode1 = solution.reverseList(listNode);
        while (listNode1 != null) {
            System.out.println(listNode1.val);
            listNode1 = listNode1.next;
        }

    }

    //手写迭代
    static class Solution3 {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            return prev;
        }
    }

    //手写递归
    class Solution4 {
        public ListNode reverseList(ListNode head) {

            if (head == null || head.next == null) return head;
            ListNode newListNode = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return newListNode;
        }
    }


}
