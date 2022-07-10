package leetcode;

import utils.ListNode;

import java.util.HashSet;
import java.util.Set;

public class code_142 {

    //哈希表
    public ListNode detectCycle(ListNode  head) {
        //重新定义一个节点
        ListNode pos = head;
        //hashmap和hashset都都可
        Set<ListNode> visited = new HashSet<>();
        while (pos != null) {
            //判断是否包含节点
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            //遍历获取下一个节点
            pos = pos.next;
        }
        return null;
    }

    // 快慢指针
    public ListNode detectCycle2(ListNode head) {
        //边界判断
        if (head == null) {
            return null;
        }
        //定义快慢指针
        ListNode slow = head, fast = head;
        while (fast != null) {
            //慢指针走一步
            slow = slow.next;
            //快指针走两步
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            //如果慢指针和快指针重合，则该节点为环形链表的入口
            if (fast == slow) {
                //重新定义一个头节点
                ListNode ptr = head;
                //循环直到该头节点赶上慢节点
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                //返回该节点
                return ptr;
            }
        }
        return null;
    }

}
