package offer;

import utils.ListNode;

import java.util.HashSet;
import java.util.Set;

public class offer_52 {

    // hash表
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            Set<ListNode> set = new HashSet<>();
            while(headA != null){
                set.add(headA);
                headA = headA.next;
            }
            while (headB != null) {
                if (set.contains(headB)) return headB;
                headB = headB.next;
            }

            return null;

        }
    }

    // 双指针
    public class Solution1 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

            if (headA == null || headB == null) return null;

            ListNode pA = headA, pB = headB;
            while (pA != pB){
                pA = pA == null ? headB : pA.next;
                pB = pB == null ? headA : pB.next;
            }

            return pA;
        }
    }

}
