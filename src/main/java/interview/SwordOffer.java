package interview;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Stack;

public class SwordOffer {

    //懒汉式单例模式
    static class Singleton{
        private static volatile Singleton instance = null;
        private Singleton(){}
        public Singleton getInstance(){
            if(instance == null){
                synchronized (Singleton.class){
                    if(instance == null){
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }

    //饿汉式单例模式
    static class SingletonE{
        private static SingletonE s = new SingletonE();
        private SingletonE(){};
        public static SingletonE getInstance(){
            return s;
        }
    }

    //3.数组中重复的数字
    public static int findRepeatNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        int repeat = -1;
        for (int i = 0; i < nums.length; i ++){
            if(!set.add(nums[i])){
                repeat = nums[i];
                break;
            }
        }
        return repeat;
    }

    //4.二维数组中的查找
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        if(matrix == null || matrix.length == 0 || matrix[0].length ==0){
            return Boolean.FALSE;
        }

        int leni = matrix.length;
        int lenj = matrix[0].length;
        int i = 0;
        int j = lenj - 1;
        while(i < leni && j >= 0){
            if(matrix[i][j] == target){
                return Boolean.TRUE;
            }else if(matrix[i][j] > target){
                i ++;
            }else {
                j --;
            }
        }

        return Boolean.FALSE;
    }

    //5.替换空格
    public String replaceSpace(String s) {

        int lenth = s.length();
        char[] array = new char[3 * lenth];
        int size = 0;
        for(int i = 0; i < lenth; i ++){
            char c = s.charAt(i);
            if(c == ' '){
                array[size ++] =  '%';
                array[size ++] =  '2';
                array[size ++] =  '0';
            }else {
                array[size ++] = c;
            }
        }
        return new String(array, 0, size);

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    //6.从尾到头打印链表
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode temp = head;
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] ints = new int[size];
        for(int i = 0; i < size; i ++){
            ints[i] = stack.pop().val;
        }
        return ints;
    }

    private ArrayList<Integer> res = new ArrayList<Integer>();
    public ArrayList<Integer> reversePrint2(ListNode head){
        if(head != null){
            res = reversePrint2(head.next);
            res.add(head.val);
        }
        return res;
    }

    public ArrayList<Integer> reversePrint3(ListNode head){

        ArrayList<Integer> arr = new ArrayList<Integer>();

        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode = null;

        while (curNode != null){
            nextNode = curNode.next;
            curNode.next = preNode;

            preNode = curNode;
            curNode = nextNode;
        }

        while(preNode != null){
            arr.add(preNode.val);
            preNode = preNode.next;
        }
        return arr;
    }

    //9.用两个栈实现队列
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    Deque<Integer> stack3;

    public void appendTail(int value) {
        stack.push(value);
    }

    public int deleteHead() {
        if(stack2.empty()){
            while(!stack.empty()){
                stack2.push(stack.pop());
            }
        }
        return stack2.pop();
    }

    //10.斐波那契数列
    public int Fibonacci(int n){
        if(n < 2){
            return n;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    public int Fibonacci2(int n){
        if(n < 2){
            return n;
        }else {
            int a = 0;
            int b = 1;
            int result = 0;
            for(int i = 1;i < n; i ++){
                result = a + b;
                a = b;
                b = result;
            }

        }


        return 0;
    }

    //11.计算旋转数组中的最小数字
    public int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while(low < high){
            int mid = low + (high - low) / 2;
            if(numbers[high] > numbers[mid]){
                high = mid;
            }else if(numbers[high] < numbers[mid]){
                low = mid + 1;
            }else{
                high -= 1;
            }
        }
        return numbers[low];
    }

    //12.矩阵中的路径
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str){
        boolean[] flag = new boolean[matrix.length];
        for(int i = 0; i < rows; i ++){
            for(int j = 0; j < cols; j ++){
                if(helper(matrix, rows, cols, i, j, str, 0, flag)){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean helper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, boolean[] flag) {
        int index = i * cols + j;
        if(i < 0 || i >= rows || j < 0 || j >= cols || flag[index] || matrix[index] != str[k]){
            return false;
        }
        if(k == str.length - 1){
            return true;
        }
        flag[index] = true;
        if(helper(matrix, rows, cols, i - 1, j, str, k, flag)
                || helper(matrix, rows, cols, i + 1, j, str, k, flag)
                || helper(matrix, rows, cols, i, j + 1, str, k, flag)
                || helper(matrix, rows, cols, i, j - 1, str, k, flag)){
            return true;
        }
        flag[index] = false;
        return false;
    }

    //15.二进制1的个数
    public int NumberOf1(int n){
        int count = 0;
        while(n!=0){
            count++;
            n=(n-1)&n;
        }
        return count;
    }

    //18.删除链表的节点
    public ListNode deleteNode(ListNode head, int val) {
        if(head.val == val){
            return head.next;
        }
        ListNode node = head;
        while(node.next.val != val){
            node = node.next;
        }
        if(node.next != null){
            node.next = node.next.next;
        }
        return node;
    }

    public ListNode deleteNodeChongFu(ListNode head, int val){
        if(head.val == val){
            return head.next;
        }
        ListNode node = head;
        int flag = node.val;
        for(int i = 0; node != null; i ++){
            if(node.val == flag){
                node.next = node.next.next;
            }
            flag = node.val;
            node = node.next;
        }
        return head;
    }

    //22.链表中倒数第k个节点
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = fast;
        for(int i = 0; i < k; i ++){
            fast = fast.next;
        }
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    //23.链表中环的入口结点
    public ListNode EntryNodeOfLoop(ListNode head){
        if(head == null || head.next == null){
            return null;
        }
        HashSet<ListNode> set = new HashSet<ListNode>();
        while(head != null){
            if(!set.add(head)){
                return head;
            }
            head = head.next;
        }
        return null;
    }

    //24.翻转链表
    public ListNode reverseList(ListNode head) {

        if(head == null || head.next == null){
            return head;
        }

        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;
        while(cur != null){
            next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode head) {

        if(head == null || head.next == null){
            return head;
        }
        ListNode newNode = reverseList2(head);
        head.next.next = head;
        head.next = null;
        return newNode;
    }

    //25.合并两个排序的链表
    public ListNode Merge(ListNode l1, ListNode l2){
        ListNode dum = new ListNode(0), cur = dum;
        while (l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next = l2;
                l2 = l1;
            }
        }
        cur.next = l1 != null ? l1 : l2;
        return cur;
    }



}
