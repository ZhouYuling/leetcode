package leetcode;

import utils.ListNode;

import java.util.HashSet;
import java.util.Set;

public class code_160 {

    //hash集合
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            //创建一个hash集合
            Set<ListNode> visited = new HashSet<ListNode>();
            ListNode temp = headA;
            //把A链表所有元素都放到hash集合中
            while (temp != null) {
                visited.add(temp);
                temp = temp.next;
            }
            temp = headB;
            //遍历B链表，hash集合存在一个B链表节点则返回
            while (temp != null) {
                if (visited.contains(temp)) {
                    return temp;
                }
                temp = temp.next;
            }
            return null;
        }
    }

    //双指针
    public class Solution2 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }
            //定义两个链表头
            ListNode pA = headA, pB = headB;
            //画图可证明，遍历一次A再遍历一次B的范围内，就能求解
            while (pA != pB) {
                pA = pA == null ? headB : pA.next;
                pB = pB == null ? headA : pB.next;
            }
            return pA;
        }
    }


}
