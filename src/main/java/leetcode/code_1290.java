package leetcode;

import utils.ListNode;

public class code_1290 {

    class Solution {
        public int getDecimalValue(ListNode head) {

            return -1;
        }

        public int getByteLevel(ListNode head){
            if (head == null) return 0;
            int byteLevel = getByteLevel(head.next) + 1;

            return 0;
        }

    }


}
