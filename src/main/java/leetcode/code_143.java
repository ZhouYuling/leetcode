package leetcode;

import utils.ListNode;
import utils.TreeNode;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class code_143 {

    public void reorderList(ListNode head) {
        ListNode dummy = head;
        List<ListNode> list = new ArrayList<>();
        while (dummy != null) {
            list.add(dummy);
            dummy = dummy.next;
        }
        int L = 0, R = list.size() - 1;
        while (L < R) {
            // 会造成死循环
//            head.next = list.get(L ++);
//            head = head.next;
//            if (L == R) break;
//            head.next = list.get(R --);
//            head = head.next;
            list.get(L ++).next = list.get(R);
            if (L == R) break;
            list.get(R --).next = list.get(L);
        }
        list.get(L).next = null;
    }

    // 寻找链表中点
    // 反转链表后半部分
    // 合并链表
    public void reorderList1(ListNode head) {
        // 寻找链表中点
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 反转链表后半部分
        ListNode tail = reverse(slow.next);
        slow.next = null;
        // 合并链表
        while (head != null && tail != null) {
            ListNode next1 = head.next;
            ListNode next2 = tail.next;
            head.next = tail;
            tail.next = next1;
            head = next1;
            tail = next2;
        }
    }

    private ListNode reverse(ListNode node) {
        ListNode prev = null, curr = node;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        code_143 code = new code_143();
        ListNode tree = Utils.createList("1,2,3,4,5");
        code.reorderList(tree);
        System.out.println(Utils.printList(tree));

        ListNode tree2 = Utils.createList("1,2,3,4,5");
        code.reorderList1(tree2);
        System.out.println(Utils.printList(tree2));

    }


}
