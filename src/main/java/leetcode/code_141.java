package leetcode;

import utils.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class code_141 {

    // 快慢指针
    public class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) return false;
            ListNode fast = head.next;
            ListNode slow = head;
            while (fast != slow) {
                if (fast == null || fast.next == null) return false;
                fast = fast.next.next;
                slow = slow.next;
            }
            return true;

        }
    }

    // 使用hash
    public class Solution2 {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) return false;
            Set<ListNode> set = new HashSet<ListNode>();
            ListNode dummy = head;
            while (dummy != null && dummy.next != null) {
                if (!set.add(dummy)) return true;
                dummy = dummy.next;
            }

            return false;

        }
    }

}
