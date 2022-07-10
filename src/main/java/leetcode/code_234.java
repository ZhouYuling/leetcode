package leetcode;

import utils.ListNode;

import java.util.ArrayList;
import java.util.List;

public class code_234 {

    //将值复制到数组中后用双指针法
    static class Solution {
        public boolean isPalindrome(ListNode head) {
            List<Integer> vals = new ArrayList<Integer>();

            // 将链表的值复制到数组中
            ListNode currentNode = head;
            while (currentNode != null) {
                vals.add(currentNode.val);
                currentNode = currentNode.next;
            }

            // 使用双指针判断是否回文
            int front = 0;
            int back = vals.size() - 1;
            while (front < back) {
                if (!vals.get(front).equals(vals.get(back))) {
                    return false;
                }
                //两个指针同时变化，判断是否是回文
                front++;
                back--;
            }
            return true;
        }
    }

    //递归
    static class Solution2 {

        private ListNode frontPointer;

        private boolean recursivelyCheck(ListNode currentNode) {
            if (currentNode != null) {
                if (!recursivelyCheck(currentNode.next)) return false;
                if (currentNode.val != frontPointer.val) return false;
                frontPointer = frontPointer.next;
            }
            return true;
        }

        //程序入口
        public boolean isPalindrome(ListNode head) {
            frontPointer = head;
            return recursivelyCheck(head);
        }
    }

    // 快慢指针
    static class Solution3 {
        //程序入口
        public boolean isPalindrome(ListNode head) {
            if (head == null) {
                return true;
            }

            // 找到前半部分链表的尾节点并反转后半部分链表
            ListNode firstHalfEnd = endOfFirstHalf(head);
            ListNode secondHalfStart = reverseList(firstHalfEnd.next);

            // 判断是否回文
            ListNode p1 = head;
            ListNode p2 = secondHalfStart;
            boolean result = true;
            while (result && p2 != null) {
                if (p1.val != p2.val) {
                    result = false;
                }
                //两个指针同时完后走
                p1 = p1.next;
                p2 = p2.next;
            }

            // 还原链表并返回结果
            firstHalfEnd.next = reverseList(secondHalfStart);
            return result;
        }

        //反转链表
        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }

        //找到链表的中点
        private ListNode endOfFirstHalf(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }
    }

    //快慢指针，不用反转和操作链表，直接遍历即可
    class Solution4 {
        public boolean isPalindrome(ListNode head) {
            if(head == null || head.next == null) {
                return true;
            }
            ListNode slow = head, fast = head;
            ListNode pre = head, prepre = null;
            while(fast != null && fast.next != null) {
                pre = slow;
                slow = slow.next;
                fast = fast.next.next;
                //反转链表
                pre.next = prepre;
                prepre = pre;
            }
            if(fast != null) {
                slow = slow.next;
            }
            while(pre != null && slow != null) {
                if(pre.val != slow.val) {
                    return false;
                }
                pre = pre.next;
                slow = slow.next;
            }
            return true;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        head.next = node1;
        node1.next = new ListNode(1);
        boolean palindrome = solution.isPalindrome(head);
        System.out.println(palindrome);

        Solution2 solution2 = new Solution2();
        boolean palindrome1 = solution2.isPalindrome(head);
        System.out.println(palindrome1);

        Solution3 solution3 = new Solution3();
        boolean palindrome2 = solution3.isPalindrome(head);
        System.out.println(palindrome2);

    }

}
