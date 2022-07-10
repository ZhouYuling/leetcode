package interview;

import utils.ListNode;
import utils.TreeNode;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

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


    //32.层序遍历二叉树
    public int[] levelOrder(TreeNode root){

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            ans.add(poll.val);
            if(poll.left != null){queue.add(root.left);}
            if(poll.right != null){queue.add(root.right);}
        }
        int[] ints = new int[ans.size()];
        for(int i = 0; i < ans.size(); i ++){
            ints[i] = ans.get(i);
        }
        return ints;
    }

    //33.前序遍历二叉树
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<Integer>();
        preorder(root, res);
        return res;
    }

    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    public List<Integer>  preOrderTreaversal(TreeNode root){
        List<Integer> res = new ArrayList<Integer>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null){
            while(node != null){
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }

        return res;
    }

    public List<TreeNode> postorderTraversal(TreeNode root){
        List<TreeNode> res = new ArrayList<TreeNode>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = null;
        while (!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.right == null || root.right == node){
                res.add(root);
                node = root;
                root = null;
            }else{
                root = stack.pop();
                root = root.right;
            }


        }

        return res;
    }

    //32.2按照每行打印二叉树
    public List<List<Integer>> levelOrder2(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> tmp = new ArrayList<>();
            for(int i = queue.size(); i > 0 ; i--){
                TreeNode poll = queue.poll();
                tmp.add(poll.val);
                if(poll.left != null){queue.offer(poll.left);}
                if(poll.right != null){queue.offer(poll.right);}
            }
            res.add(tmp);
        }
        return res;
    }

    //32.3奇数行从左到右，偶数行从右到左
    public List<List<Integer>> levelOrder3(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            LinkedList<Integer> tmp = new LinkedList<>();
            for (int i = queue.size(); i > 0; i ++){
                TreeNode node = queue.poll();
                if(res.size() % 2 == 0){
                    tmp.addLast(node.val);
                }else{
                    tmp.addFirst(node.val);
                }
                if(node.left != null)queue.offer(node.left);
                if(node.right != null)queue.offer(node.right);
            }
            res.add(tmp);
        }
        return res;
    }

    //40.最小的k个数
    public int[] getLeastNumbers(int[] arr, int k){
        int[] vec = new int[k];
        if(arr.length == 0){
            return vec;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        for(int i = 0; i < k; i ++){
            queue.add(arr[i]);
        }
        for(int i = k; i < arr.length; i ++){
            if(queue.peek() > arr[i]){
                queue.poll();
                queue.add(arr[i]);
            }
        }
        for(int i = 0 ; i < k; i ++){
            vec[i] = queue.poll();
        }
        return vec;
    }

    //41.数据流中的中位数
    class MedianFinder {

        /** initialize your data structure here. */
        Queue<Integer> A,B;
        public MedianFinder() {
            A = new PriorityQueue<Integer>();
            B = new PriorityQueue<Integer>((o1, o2)->o2 -o1);
        }

        public void addNum(int num) {
            if(A.size() != B.size()){
                //向A中插入一个数字
                B.add(num);
                A.offer(B.poll());
            }else{
                //向B中插入一个数字
                A.add(num);
                B.add(A.poll());
            }
        }

        public double findMedian() {
            return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;
        }
    }

    //
    public void arrayListLinkedList(){
        ArrayList<String> arr = new ArrayList<>();
        LinkedList<String> link = new LinkedList<>();
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(199);
        queue.add("");
        queue.remove();
        queue.offer("");
        queue.poll();

        try {
            queue.put("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            String take = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PriorityQueue<Integer> integers = new PriorityQueue<>();
        PriorityBlockingQueue<Integer> integerPriorityBlockingQueue = new PriorityBlockingQueue<Integer>();

    }

    public static void main(String[] args) {
        char a = '周';
        ConcurrentHashMap<String, String> stringStringConcurrentHashMap = new ConcurrentHashMap<>();
        System.out.println(a);
    }



}
