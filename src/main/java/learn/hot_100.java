package learn;

import sun.nio.cs.ext.MacHebrew;
import utils.ListNode;
import utils.TreeNode;
import utils.Utils;

import java.util.*;

public class hot_100 {

    // 1.两数之和
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    // 两链表相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) tail.next = new ListNode(carry);

        return head;
    }

    // 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s){
        if (s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i]))
                left = Math.max(left, map.get(chars[i]) + 1);
            map.put(chars[i], i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s){
        if (s.length() == 0) return 0;
        Set<Character> occ = new HashSet<>();
        int max = 0, right = -1;
        for (int i = 0; i < s.length(); i++) {
            if (i != 0) occ.remove(s.charAt(i - 1));
            while (right + 1 < s.length() && !occ.contains(s.charAt(right + 1))) {
                occ.add(s.charAt(right + 1));
                right ++;
            }
            max = Math.max(max, right - i + 1);
        }
        return max;
    }

    // 寻找两个正序数组中的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length,length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            return getKthElement(nums1, nums2, midIndex + 1);
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            return getKthElement(nums1, nums2, midIndex1) + getKthElement(nums1, nums2, midIndex2) / 2.0;

        }
    }

    private double getKthElement(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;

        while (true) {
            if (index1 == length1) return nums2[index2 + k - 1];
            if (index2 == length2) return nums1[index1 + k - 1];
            if (k == 1) return Math.min(nums1[index1], nums2[index2]);

            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            if (nums1[newIndex1] <= nums2[newIndex2]) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    // 最长回文子串
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        int len = s.length();

        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {
                int j = L + i - 1;
                if (j >= len) break;
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) dp[i][j] = true;
                    else dp[i][j] = dp[i + 1][j - 1];
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) return s;
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            -- left;
            ++ right;
        }
        return right - left - 1;
    }

    // 10.正则表达式匹配
    public boolean isMatch(String s, String p){
        int m = s.length();
        int n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j < n; j++) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) f[i][j] = f[i][j] || f[i - 1][j];
                } else {
                    if (matches(s, p, i, j - 1)) f[i][j] = f[i - 1][j - 1];
                }
            }
        }

        return false;
    }

    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) return false;
        if (p.charAt(j - 1) == '.') return true;
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    // 11.盛最多水的容器
    public int maxArea(int[] height) {
        int L = 0, R = height.length - 1;
        int ans = 0;
        while (L < R) {
            int area = Math.min(height[L], height[R]) * (R - L);
            ans = Math.max(ans, area);
            if (height[L] < height[R]) L ++;
            else R --;
        }
        return ans;
    }

    // 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) return ans;
        int len = nums.length;
        //Arrays.sort(nums, (o1, o2) -> o1 - o2);
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) L ++;
                    while (L < R && nums[R] == nums[R - 1]) R --;
                    L ++;
                    R --;
                } else if (sum < 0) L ++;
                else if (sum > 0) R --;
            }
        }
        return ans;
    }

    // 电话号码的字母组合
    public List<String> letterCombinations(String digits){
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0) return combinations;
        Map<Character, String> phoneMap = new HashMap<Character, String>(){{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, "");
        return combinations;
    }

    private void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, String s) {
        if (index == digits.length()) combinations.add(s);
        else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                backtrack(combinations, phoneMap, digits, index + 1, s + letters.charAt(i));
            }
        }
    }

    // 删除链表的倒数第 N 个结点
    // 遍历链表长度
    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode dummy = new ListNode(0, head);
        int length = getLenth(head);
        ListNode cur = dummy;
        for (int i = 0; i < length - n + 1; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    private int getLenth(ListNode head) {
        int length = 0;
        while (head != null) {
            ++ length;
            head = head.next;
        }
        return length;
    }

    // 栈
    public ListNode removeNthFromEnd2(ListNode head, int n){
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummy;
        while(cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        if (prev != null) {
            prev.next = prev.next.next;
        }
        return dummy.next;
    }

    // 双指针
    public ListNode removeNthFromEnd3(ListNode head, int n){
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    // 20.有效的括号
    public boolean isValid(String s){
        if (s == null || s.length() % 2 == 1) return false;
        int len = s.length();
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) return false;
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    // 21. 合并两个有序链表
    // 递归
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if (l1 == null) return l2;
        else if (l2 == null) return l1;
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    // 迭代
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    // 22. 括号生成
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    private void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (isValid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    private boolean isValid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == 'c') balance ++;
            else balance --;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    // 回溯
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }

    }

    // 23. 合并K个升序链表
    // 顺序合并
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; i++) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int L, int R) {
        if (L == R) return lists[L];
        if (L > R) return null;
        int mid = (L + R) >> 1;
        return mergeTwoLists(merge(lists, L, mid), merge(lists, mid + 1, R));
    }

    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr){
            this.val = val;
            this.ptr = ptr;
        }

        @Override
        public int compareTo(Status o) {
            return this.val - o.val;
        }
    }

    PriorityQueue<Status> queue_23 = new PriorityQueue<>();

    public ListNode mergeKLists3(ListNode[] lists) {
        for (ListNode node : lists) {
            if (node != null)
                queue_23.offer(new Status(node.val, node));
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue_23.isEmpty()) {
            Status f = queue_23.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null)
                queue_23.offer(new Status(f.ptr.next.val, f.ptr.next));
        }
        return head.next;
    }

    // 31. 下一个排列
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1])
            i --;
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) j --;
            Utils.swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            Utils.swap(nums, left, right);
            left ++;
            right --;
        }
    }

    // 32. 最长有效括号
    // 动态规划
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }

        return -1;
    }

    // 栈
    public int longestValidParentheses2(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(i);
            else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }

        return maxans;
    }

    // 双指针
    public int longestValidParentheses3(String s) {

        int left = 0, right = 0, maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left ++;
            else right ++;
            if (left == right) maxLength = Math.max(maxLength, 2 * right);
            else if (right > left) left = right = 0;
        }
        left = right = 0;
        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i) == '(') left ++;
            else right ++;
            if (left == right) maxLength = Math.max(maxLength, 2 * left);
            else if (left > right) left = right = 0;
        }

        return maxLength;
    }

    // 33. 搜索旋转排序数组
    // 二分查找
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int len = nums.length;
        if (len == 1) return nums[0] == target ? 0 : 1;
        int L = 0, R = len - 1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (nums[mid] == target) return mid;
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) R = mid - 1;
                else L = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[len - 1]) L = mid + 1;
                else R = mid - 1;
            }
        }

        return -1;
    }

    // 34. 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target)
            return new int[]{leftIdx, rightIdx};
        return new int[]{-1, -1};
    }

    private int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    // 39. 组合总和
    // 回溯
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) return;
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        dfs(candidates, target, ans, combine, idx + 1);
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }

    // 42. 接雨水
    // 动态规划
    public int trap(int[] height){
        if (height == null || height.length == 0) return 0;
        int n = height.length;
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return -1;
    }

    // 单调栈
    public int trap2(int[] height){
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) break;
                int left = stack.peek();
                int curWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currHeight * curWidth;
            }
            stack.push(i);
        }
        return ans;
    }

    // 双指针
    public int trap3(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(right, height[rightMax]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++ left;
            }else {
                ans += rightMax - height[right];
                -- rightMax;
            }
        }

        return ans;
    }

    // 46. 全排列
    // 回溯
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for (int num: nums) {
            output.add(num);
        }
        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    private void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        if (first == n) res.add(new ArrayList<>(output));
        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);
            backtrack(n, output, res, first + 1);
            Collections.swap(output, first, i);
        }
    }

    // 48. 旋转图像
    // 辅助数组
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] matirx_new = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matirx_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = matirx_new[i][j];
            }
        }
    }

    // 原地旋转
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    // 用反转代替旋转
    public void rotate3(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // 49. 字母异位词分组
    // 排序
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str :
                strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    // 计数
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str :
                strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a'] ++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    // 53. 最大子连续数组和
    // 动态规划
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int pre = 0, maxAns = nums[0];
        for (int x :
                nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    // 55. 跳跃游戏
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; i++) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    // 合并区间
    public int[][] merge(int[][] intervals) {

        if (intervals == null || intervals.length < 2) return intervals;
        int len = intervals.length;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        for (int i = 1; i < len; i++) {
            int[] curInterval = intervals[i];
            int[] peek = res.get(res.size() - 1);
            if (curInterval[0] > peek[1]) res.add(curInterval);
            else peek[1] = Math.max(curInterval[1], peek[1]);
        }
        return res.toArray(new int[res.size()][]);
    }

    // 62. 不同路径
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }

    // 64. 最小路径和
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < columns; i++) {
            dp[0][i] = dp[0][i] + grid[0][i];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][columns - 1];
    }

    // 70. 爬楼梯
    // 动态规划
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    // 矩阵快速幂
    public int climbStairs2(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    private int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) ret = multiply(ret, a);
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    private int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    // 72. 编辑距离
    public int minDistance(String word1, String word2) {
        // if (word1 == null && word2 == null) return 0;
        // if (word1 == null || word2 == null) return word1 == null ? word2.length() : word1.length();
        int n = word1 == null ? 0 : word1.length();
        int m = word2 == null ? 0 : word2.length();
        if (n * m == 0) return n + m;
        int[][] D = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1))
                    left_down += 1;
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    // 75. 颜色分类
    // 单指针
    public void sortColors(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                Utils.swap(nums, i, ptr);
                ptr ++;
            }
        }
        for (int i = ptr; i < n; i++) {
            if (nums[i] == 1) {
                Utils.swap(nums, i, ptr);
                ++ptr;
            }
        }
    }

    // 双指针
    public void sortColors2(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                Utils.swap(nums, i, p1);
            } else if (nums[i] == 0) {
                Utils.swap(nums, i, p0);
                if (p0 < p1) Utils.swap(nums, i, p1);
                ++p0;
                ++p1;
            }
        }
    }

    // 双指针
    public void sortColors3(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; i++) {
            while (i <= p2 && nums[i] == 2) {
                Utils.swap(nums, i, p2);
                -- p2;
            }
            if (nums[i] == 0) {
                Utils.swap(nums, i, p0);
                ++p0;
            }
        }
    }

    // 76. 最小覆盖子串
    // 滑动窗口
    public String minWindow(String s, String t) {
        Map<Character, Integer> ori = new HashMap<>();
        Map<Character, Integer> cnt = new HashMap<>();
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        // 窗口左右指针
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++ r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check(ori, cnt) && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    private boolean check(Map<Character, Integer> ori, Map<Character, Integer> cnt) {
        Iterator<Map.Entry<Character, Integer>> iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Character, Integer> next = iter.next();
            Character key = next.getKey();
            Integer value = next.getValue();
            if (cnt.getOrDefault(key, 0) < value) return false;
        }
        return true;
    }

    // 78. 子集
    // 迭代法实现子集枚举
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> t = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); mask++) {
            t.clear();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) t.add(nums[i]);
            }
            ans.add(new ArrayList<>(t));
        }
        return ans;
    }

    // 递归法实现子集枚举
    public List<List<Integer>> subsets2(int[] nums) {
        List<Integer> t = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        dfs(0, nums, ans, t);
        return ans;
    }

    private void dfs(int cur, int[] nums, List<List<Integer>> ans, List<Integer> t) {
        if (cur == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums, ans, t);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums, ans, t);
    }

    // 79. 单词搜索
    public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) return true;
            }
        }
        return false;
    }

    private boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) return false;
        else if (k == s.length() - 1) return true;
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir :
                directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }

    // 84. 柱状图中最大的矩形
    // 两次遍历，使用两个单调栈
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 单调递增
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) stack.pop();
            left[i] = (stack.isEmpty() ? - 1 : stack.peek());
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) stack.pop();
            right[i] = (stack.isEmpty() ? n : stack.peek());
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    // 单调栈 + 常数优化
    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    // 94. 二叉树的中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if(root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    // 迭代
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    // Morris中序遍历


    // 96. 不同的二叉搜索树
    // 动态规划
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }

        return G[n];
    }

    // 98.验证二叉搜索树
    //
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) return true;
        if (node.val <= lower || node.val >= upper) return false;
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        double inorder = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return false;
    }

    // 101. 对称二叉树
    // 递归
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    // 迭代
    public boolean isSymmetric2(TreeNode root) {
        return check2(root, root);
    }

    private boolean check2(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            // 已经遍历的叶子节点了，还需要查看其他节点是否相等
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }

            // 先进先出位置不能变
            q.offer(u.left);
            q.offer(v.right);

            // 先进先出位置不能变
            q.offer(u.right);
            q.offer(v.left);
        }
        return false;
    }

    // 102. 二叉树的层序遍历
    // 广度遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = queue.size();
            for (int i = 0; i <= currentLevelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            ret.add(level);
        }
        return null;
    }

    // 层序遍历

    // 104. 二叉树的最大深度
    // 方法一：深度优先遍历
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // 广度优先遍历


    // 105. 从前序与中序遍历序列构造二叉树
    // 递归
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1, indexMap);
    }

    private TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right, Map<Integer, Integer> indexMap) {
        if (preorder_left > preorder_right) return null;

        int preorder_root = preorder_left;
        int inorder_root = indexMap.get(preorder[preorder_root]);

        TreeNode root = new TreeNode(preorder[preorder_root]);
        int size_left_subtree = inorder_root - inorder_left;
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1, indexMap);
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right, indexMap);
        return root;
    }

    // 114. 二叉树展开为链表
    // 方法一：前序遍历
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    private void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }

    // 迭代
    public void flatten2(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    // 前序遍历和展开同步
    public void flatten3(TreeNode root) {
        if (root == null) return;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (prev != null) {
                prev.left = null;
                prev.right = curr;
            }
            TreeNode left = curr.left, right = curr.right;
            if (right != null) stack.push(right);
            if (left != null) stack.push(left);
            prev = curr;
        }
    }

    public void flatten4(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) predecessor = predecessor.right;
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }

    // 121. 买卖股票的最佳时机
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) minprice = prices[i];
            else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }

        return maxprofit;
    }

    // 124. 二叉树中的最大路径和
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) return 0;
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        int priceNewpath = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, priceNewpath);
        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

    // 128. 最长连续序列
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num :
                nums) {
            num_set.add(num);
        }

        int longestStreak = 0;
        for (int num :
                num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }

    // 136. 只出现一次的数字
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num:
             nums) {
            single ^= num;
        }
        return single;
    }

    // 139. 单词拆分
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    // 141. 环形链表
    public boolean hasCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        while (head != null) {
            if (!seen.add(head)) return true;
            head = head.next;
        }
        return false;
    }

    // 快慢指针
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    // 142. 环形链表 II
    public ListNode detectCycle(ListNode head) {
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<>();
        while (pos != null) {
            if (visited.contains(pos)) return pos;
            else visited.add(pos);
            pos = pos.next;
        }
        return null;
    }

    // 快慢指针
    public ListNode detectCycle2(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow= slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else return null;
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    // 146. LRU 缓存
    public class LRUCache {

        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
            public DLinkedNode(){}
            public DLinkedNode(int key, int value){
                this.key = key;
                this.value = value;
            }
        }

        private Map<Integer, DLinkedNode> cache = new HashMap<>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        public LRUCache(int capacity){
            this.size = 0;
            this.capacity = capacity;
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key){
            DLinkedNode node = cache.get(key);
            if (node == null) return -1;
            moveTohead(node);
            return node.value;
        }

        public void put(int key, int value){
            DLinkedNode node = cache.get(key);
            if (node == null) {
                DLinkedNode newNode = new DLinkedNode(key, value);
                cache.put(key, newNode);
                addToHead(newNode);
                ++size;
                if (size > capacity) {
                    DLinkedNode tail = removeTail();
                    cache.remove(tail.key);
                    -- size;
                }
            } else {
                node.value = value;
                moveTohead(node);
            }
        }

        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }

        private void moveTohead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    // 148. 排序链表
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }

    // 方法一：自顶向下归并排序
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    private ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) return head;
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) fast = fast.next;
        }
        ListNode mid = slow;
        ListNode left = sortList(head, mid);
        ListNode right = sortList(mid, tail);
        return merge(left, right);
    }

    public ListNode sortList2(ListNode head) {
        if (head == null) return head;
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length ++;
            node = node.next;
        }
        ListNode dummy = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummy, curr = dummy.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                prev.next = merge(head1, head2);
                while (prev.next != null) prev = prev.next;
                curr = next;
            }
        }

        return dummy.next;
    }

    // 152. 乘积最大子数组
    // 动态规划
    public int maxProduct(int[] nums) {
        int length = nums.length;
        // 代表第 i 个元素结尾的乘积最大子数组的乘积 fmax(i)
        int[] maxF = new int[length];
        // 代表第 i 个元素结尾的乘积最小子数组的乘积 fmin(i)
        int[] minF = new int[length];
        maxF[0] = minF[0] = nums[0];
        for (int i = 1; i < length; i++) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; i++) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }

    public int maxProduct2(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }

    // 155. 最小栈
    class MinStack {
        Deque<Integer> xStack;
        Deque<Integer> minStack;

        public MinStack() {
            xStack = new LinkedList<>();
            minStack = new LinkedList<>();
            minStack.push(Integer.MIN_VALUE);
        }

        public void push(int x){
            xStack.push(x);
            minStack.push(Math.min(minStack.peek(), x));
        }

        public void pop(){
            xStack.pop();
            minStack.pop();
        }

        public int top(){
            return xStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }

    }

    // 160. 相交链表
    // 哈希表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) return temp;
            temp = temp.next;
        }
        return null;
    }

    // 双指针
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    // 169. 多数元素
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);
        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) majorityEntry = entry;
        }
        return majorityEntry != null ? majorityEntry.getKey() : -1;
    }

    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0));
        }
        return counts;
    }

    // 投票
    public int majorityElement2(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }

    // 198. 打家劫舍
    public int rob(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        int length = nums.length;
        if (length == 1) return nums[0];
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    // 滚动数组
    public int rob2(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        int length = nums.length;
        if (length == 1) return nums[0];
        int[] dp = new int[length];
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    // 200. 岛屿数量
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c>= nc || grid[r][c] == '0') return;
        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int nr = grid.length;
        int nc = grid[0].length;
        int num_isLands = 0;
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == 'r') {
                    ++ num_isLands;
                    dfs(grid, r, c);
                }
            }
        }
        return num_isLands;
    }

    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    ++ num_islands;
                    grid[r][c] = '0';
                    Queue<int[]> neighbors = new LinkedList<>();
                    neighbors.offer(new int[]{r, c});
                    while (!neighbors.isEmpty()) {
                        int[] id = neighbors.poll();
                        int row = id[0], col = id[1];
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            neighbors.add(new int[]{row - 1, col});
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row + 1][col] == '1') {
                            neighbors.add(new int[]{row + 1, col});
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            neighbors.add(new int[]{row, col - 1});
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 >= 0 && grid[row][col + 1] == '1') {
                            neighbors.add(new int[]{row, col + 1});
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }

    // 206. 反转链表
    // 迭代
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

    // 递归
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // 207. 课程表
    // 深度优先遍历

    // 广度优先遍历

    // 208. 实现 Trie (前缀树)
    class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie(){
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word){
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) node.children[index] = new Trie();
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix2(word);
            return node != null && node.isEnd;
        }

        public boolean startWith(String prefix) {
            return searchPrefix2(prefix) != null;
        }

        private Trie searchPrefix2(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) return null;
                node = node.children[index];
            }
            return node;
        }
    }

    // 数组中的第K个最大元素
    // 堆排序
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            Utils.swap(nums, 0, i);
            -- heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    private void buildMaxHeap(int[] nums, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeapify(nums, i, heapSize);
        }
    }

    private void maxHeapify(int[] nums, int i, int heapSize) {
        int L = i * 2 + 1, R = i * 2 + 2, largest = i;
        if (L < heapSize && nums[L] > nums[largest]) largest = L;
        if (R < heapSize && nums[R] > nums[largest]) largest = R;
        if (largest != i) {
            Utils.swap(nums, i, largest);
            maxHeapify(nums, largest, heapSize);
        }
    }

    // 快速排序
    Random rand = new Random();
    public int findKthLargest2(int[] nums, int k) {
        return quickSort(nums, k, 0, nums.length - 1);
    }

    private int quickSort(int[] nums, int k, int left, int right) {
        int index = rand.nextInt(right - left + 1) + left;
        int flag = nums[index];
        nums[index] = nums[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] <= flag) j --;
            nums[i] = nums[j];
            while (i < j && nums[i] >= flag) i ++;
            nums[j] = nums[i];
        }
        nums[i] = flag;
        if (i == k - 1) return nums[i];
        else if (i < k - 1) return quickSort(nums, k, i + 1, right);
        else return quickSort(nums, k, left, i - 1);
    }

    // 221. 最大正方形
    // 动态规划
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return maxSide;
        int rows = matrix.length, colums = matrix[0].length;
        int[][] dp = new int[rows][colums];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) dp[i][j] = 1;
                    else dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }
        return maxSide * maxSide;
    }

    // 226. 翻转二叉树
    // 广度优先遍历
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return null;
    }
    // 深度遍历
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;
    }
    //递归
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree3(root.left);
        TreeNode right = invertTree3(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // 234. 回文链表
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> vals = new ArrayList<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) return false;
            front ++;
            back --;
        }
        return true;
    }
    // 递归
    private ListNode frontPointer;
    public boolean isPalindrome2(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }
    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) return false;
            if (currentNode.val != frontPointer.val) return false;
            frontPointer = frontPointer.next;
        }
        return true;
    }
    // 快慢指针
    public boolean isPalindrome3(ListNode head) {
        if (head == null) return true;
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList3(firstHalfEnd.next);
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) result = false;
            p1 = p1.next;
            p2 = p2.next;
        }
        firstHalfEnd.next = reverseList3(secondHalfStart);
        return result;
    }

    private ListNode reverseList3(ListNode head) {
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

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    //236. 二叉树的最近公共祖先
    //递归
    private TreeNode ans;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) ans = root;
        return lson || rson || (root.val == p.val || root.val == q.val);
    }
    // 递归第二种
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left == null && right == null) return null;
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
    // 储存父节点
    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) return q;
            q = parent.get(q.val);
        }
        return null;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }
    // 238. 除自身以外数组的乘积
    // 左右乘积列表
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] L = new int[length];
        int[] R = new int[length];
        int[] ans = new int[length];
        L[0] = 1;
        for (int i = 1; i < length; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }
        R[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }
        for (int i = 0; i < length; i++) {
            ans[i] = L[i] * R[i];
        }
        return ans;
    }
    // 优化
    public int[] productExceptSelf2(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            answer[i] = answer[i] * R;
            R *= nums[i];
        }
        return answer;
    }
    // 239. 滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        if (k == 0) return new int[0];
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }
        if (nums.length <= k) return new int[]{pq.peek()[0]};
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; i++) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
    // 单调队列
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k)
                deque.pollLast();
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
    // 240. 搜索二维矩阵 II
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int index = search2(row, target);
            if (index >= 0) return true;
        }
        return false;
    }
    private int search2(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) return mid;
            else if (num > target) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }
    // Z字形查找
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) return true;
            if (matrix[x][y] > target) --y;
            else ++x;
        }
        return false;
    }
    // 253. 会议室 II
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        PriorityQueue<Integer> allocator = new PriorityQueue<>(intervals.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        allocator.add(intervals[0][1]);
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] >= allocator.peek()) allocator.poll();
            allocator.add(intervals[i][1]);
        }
        return allocator.size();
    }
    // 有序化
    public int minMeetingRooms2(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(end, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        Arrays.sort(start, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int startPointer = 0, endPointer = 0;
        int usedRooms = 0;
        while (startPointer < intervals.length) {
            if (start[startPointer] >= end[endPointer]) {
                usedRooms -= 1;
                endPointer += 1;
            }
            usedRooms += 1;
            startPointer += 1;
        }
        return usedRooms;
    }

    // 279. 完全平方数
    // 动态规划
    public int numSquares(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }
    // 283. 移动零
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                Utils.swap(nums, left, right);
                left ++;
            }
            right ++;
        }
    }
    // 287. 寻找重复数
    //
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int L = 1, R = n - 1, ans = -1;
        while (L <= R) {
            int mid = (L + R) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) cnt ++;
            }
            if (cnt <= mid) L = mid + 1;
            else {
                R = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    public int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    // 297. 二叉树的序列化与反序列化
    // 前序遍历
    public class Codec {
        public String serialize(TreeNode root) {
            if (root == null) return "null";
            return root.val + "," + serialize(root.left) + "," + serialize(root.right);
        }
        public TreeNode deserialize(String data) {
            String[] req = data.split(",");
            List<String> r = new ArrayList<>(Arrays.asList(req));
            return dfsdeserialize(r);
        }
        private TreeNode dfsdeserialize(List<String> r) {
            if ("null".equals(r.get(0))) {
                r.remove(0);
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(r.get(0)));
            r.remove(0);
            node.left = dfsdeserialize(r);
            node.right = dfsdeserialize(r);
            return node;
        }
    }
    // 后序遍历
    public class Codec2 {
        public String serialize(TreeNode root) {
            if (root == null) return "null";
            return serialize(root.left) + "," + serialize(root.right) + "," + root.val;
        }
        public TreeNode deserialize(String data) {
            String[] req = data.split(",");
            List<String> r = new ArrayList<>(Arrays.asList(req));
            return dfsdeserialize(r);
        }

        private TreeNode dfsdeserialize(List<String> r) {
            if ("null".equals(r.get(r.size() - 1))) {
                r.remove(r.size() - 1);
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(r.get(r.size() - 1)));
            r.remove(r.size() - 1);
            node.right = dfsdeserialize(r);
            node.left = dfsdeserialize(r);
            return null;
        }
    }

    public class Codec3 {
        public String serialize(TreeNode root) {
            if (root == null) return "";
            List<String> res = new ArrayList<>();
            LinkedList<TreeNode> qu = new LinkedList<>();
            qu.addFirst(root);
            while (!qu.isEmpty()) {
                TreeNode node = qu.removeLast();
                if (node == null) {
                    res.add("null");
                    continue;
                } else {
                    res.add(String.valueOf(node.val));
                    qu.addFirst(node.left);
                    qu.addFirst(node.right);
                }
            }
            StringBuilder r = new StringBuilder();
            for (int i = 0; i < res.size(); i++) {
                r.append(res.get(i));
                if (i < res.size() - 1) r.append(",");
            }
            return r.toString();
        }
        public TreeNode deserialize(String data) {
            if (data.length() == 0) return null;
            String[] req = data.split(",");
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(req[0]));
            int index=1;
            while (index<req.length) {
                TreeNode node = queue.poll();
                if (!"null".equals(req[index])) {
                    node.left = new TreeNode(Integer.parseInt(req[index]));
                    queue.offer(node.left);
                }
                index++;
                if(!("null".equals(req[index]))){
                    node.right=new TreeNode(Integer.parseInt(req[index]));
                    queue.offer(node.right);
                }
                index++;
            }
            return root;
        }
    }

    // 300. 最长递增子序列
    // 动态规划
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
    // 贪心 + 二分查找
    public int lengthOfLIS2(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) return 0;
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) d[++len] = nums[i];
            else {
                int L = 1, R = len, pos = 0;
                while (L < R) {
                    int mid = (L + R) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        L = mid + 1;
                    } else {
                        R = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
    // 301. 删除无效的括号
    private List<String> res = new ArrayList<String>();
    public List<String> removeInvalidParentheses(String s) {
        int lremove = 0;
        int rremove = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') lremove ++;
            else if (s.charAt(i) == ')') {
                if (lremove == 0) rremove ++;
                else lremove --;
            }
        }
        helper(s, 0, lremove, rremove);
        return res;
    }

    private void helper(String str, int start, int lremove, int rremove) {
        if (lremove == 0 && rremove == 0) {
            if (isValid2(str)) res.add(str);
            return;
        }
        for (int i = start; i < str.length(); i++) {
            if (i != start && str.charAt(i) == str.charAt(i - 1)) continue;
            if (lremove + rremove > str.length() - i) return;
            String s = str.substring(0, i) + str.substring(i + 1);
            if (lremove > 0 && str.charAt(i) == '(')
                helper(s, i, lremove - 1, rremove);
            if (rremove > 0 && str.charAt(i) == ')')
                helper(s, i, lremove, rremove - 1);
        }
    }
    private boolean isValid2(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') cnt ++;
            else if (str.charAt(i) == ')') {
                cnt --;
                if (cnt < 0) return false;
            }
        }

        return false;
    }
    public List<String> removeInvalidParentheses2(String s) {
        List<String> ans = new ArrayList<>();
        Set<String> currSet = new HashSet<>();
        currSet.add(s);
        while (true) {
            for (String str : currSet) {
                if (isValid2(str))
                    ans.add(str);
            }
            if (ans.size() > 0) return ans;
            Set<String> nextSet = new HashSet<>();
            for (String str :
                    currSet) {
                for (int i = 0; i < str.length(); i ++) {
                    if (i > 0 && str.charAt(i) == str.charAt(i - 1)) continue;
                    if (str.charAt(i) == '(' || str.charAt(i) == ')')
                        nextSet.add(str.substring(0, i) + str.substring(i + 1));
                }
            }
            currSet = nextSet;
        }
    }
    // 309. 最佳买卖股票时机含冷冻期
    // 动态规划
    // f[i][0]: 手上持有股票的最大收益
    // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
    // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
    public int maxProfit2(int[] prices) {
        if (prices.length == 0) return 0;
        int n = prices.length;
        int[][] f = new int[n][3];
        f[0][0] = - prices[0];
        for (int i = 1; i < n; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][0], f[i - 1][2]);
        }
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }

    public int maxProfit3(int[] prices) {
        if (prices.length == 0) return 0;
        int n = prices.length;
        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;
        for (int i = 1; i < n; i++) {
            int newf0 = Math.max(f0, f2 - prices[i]);
            int newf1 = f0 + prices[i];
            int newf2 = Math.max(f1, f2);
            f0 = newf0;
            f1 = newf1;
            f2 = newf2;
        }
        return -1;
    }
    // 312. 戳气球

    // 322 零钱兑换
    // 记忆搜索法
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min) min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
    // 动态规划
    public int coinChange2(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i)
                    dp[i] = Math.min(dp[i], dp[i - coins[j] + 1]);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
    // 337. 打家劫舍 III
    // f(o) 表示选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和；
    // g(o) 表示不选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和；
    // l 和 r 代表 o 的左右孩子。
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();
    public int rob(TreeNode root){
        dfs1(root);
        return -1;
    }

    private void dfs1(TreeNode node) {
        if (node == null) return;
        dfs1(node.left);
        dfs1(node.right);
        f.put(node, node.val + f.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0))
                +
                Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }
    // 动态规划优化
    public int rob2(TreeNode root) {
        int[] rootStatus = dfs2(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    private int[] dfs2(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        int[] L = dfs2(node.left);
        int[] R = dfs2(node.right);
        // 1 不选择 0 选择
        int selected = node.val + L[1] + R[1];
        int notSelected = Math.max(L[0], L[1]) + Math.max(R[0], R[1]);
        return new int[]{selected, notSelected};
    }
    // 338. 比特位计数
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            bits[i] = countOnes(i);
        }
        return bits;
    }
    private int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            x &= (x - 1);
            ones ++;
        }
        return ones;
    }
    // 动态规划 最高有效位
    public int[] countBits2(int n) {
        int[] bits = new int[n + 1];
        int hightBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                hightBit = i;
            }
            bits[i] = bits[i - hightBit] + 1;
        }
        return bits;
    }
    public int[] countBits3(int n) {
        int[] bits = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }
    public int[] countBits4(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }
    // 347. 前 K 个高频元素
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurrence = new HashMap<>();
        for (int num : nums) {
            occurrence.put(num, occurrence.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry: occurrence.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> occurences = new HashMap<>();
        for (int num: nums) {
            occurences.put(num, occurences.getOrDefault(num, 0) + 1);
        }
        List<int[]> values = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: occurences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        int[] ret = new int[k];
        qsort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    private void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int)(Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);
        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index ++;
            }
        }
        Collections.swap(values, start, index);
        if (k <= index - start) {
            qsort(values, start, index - 1, ret, retIndex, k);
        } else {
            for (int i = start; i <= index; i++) {
                ret[retIndex ++] = values.get(i)[0];
            }
            if (k > index - start + 1) {
                qsort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }

    }

    // 394. 字符串解码

    // 399. 除法求值

    // 406. 根据身高重建队列
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                if (p1[0] != p2[0]) {
                    return p1[0] - p2[0];
                } else {
                    return p2[1] - p1[1];
                }
            }
        });
        int n = people.length;
        int[][] ans = new int[n][];
        for (int[] person : people) {
            int spaces = person[1] + 1;
            for (int i = 0; i < n; i++) {
                if (ans[i] == null) {
                    -- spaces;
                    if (spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            // 身高从高到低，下标从低到高
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            // 在此列表中的指定位置index插入指定的元素(身高)。 将当前位于该位置的元素(如果有)和任何后续元素(向其索引添加一个)移动。
            // 身高从高到矮，所以后面往前面插入，不会影响前面排序
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }

    // 416. 分割等和子集
    // 动态规划
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) return false;
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        if (maxNum > target) return false;
        // dp[i][j] 表示从数组的 [0,i] 下标范围内选取若干个正整数（可以是 0 个），是否存在一种选取方案使得被选取的正整数的和等于 j
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                }
            }
        }
        return dp[n - 1][target];
    }
    // 437. 路径总和 III
    // 深度优先搜索
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        int ret = rootSum(root, targetSum);
        ret += pathSum(root.left, targetSum);
        ret += pathSum(root.right, targetSum);
        return ret;
    }
    private int rootSum(TreeNode root, int targetSum) {
        int ret = 0;
        if (root == null) return 0;
        int val = root.val;
        if (val == targetSum) ret ++;
        ret += rootSum(root.left, targetSum - val);
        ret += rootSum(root.right, targetSum - val);
        return ret;
    }

    public int pathSum2(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    private int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) return 0;
        int ret = 0;
        curr += root.val;
        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);
        return ret;
    }
    // 438. 找到字符串中所有字母异位词
    // 滑动窗口
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; i++) {
            sCount[s.charAt(i) - 'a'] ++;
            pCount[p.charAt(i) - 'a'] ++;
        }
        if (Arrays.equals(sCount, pCount)) ans.add(0);
        for (int i = 0; i < sLen - pLen; i++) {
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + pLen) - 'a'];
            if (Arrays.equals(sCount, pCount)) ans.add(i + 1);
        }
        return ans;
    }
    // 优化以后的滑动窗口
    public List<Integer> findAnagrams2(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int[] count = new int[26];
        for (int i = 0; i < pLen; i++) {
            ++ count[s.charAt(i) - 'a'];
            -- count[p.charAt(i) - 'a'];
        }
        int diff = 0;
        for (int j = 0; j < 26; j++) {
            if (count[j] != 0)
                ++diff;
        }
        if (diff == 0) ans.add(0);
        for (int i = 0; i < sLen - 1; i++) {
            int tag = count[s.charAt(i) - 'a'];
            if (tag == 1) -- diff;
            else if (tag == 0) ++ diff;
            --count[s.charAt(i) - 'a'];

            int tag2 = count[s.charAt(i + pLen) - 'a'];
            if (tag2 == -1) -- diff;
            else if (tag2 == 0) ++ diff;
            count[s.charAt(i + pLen) - 'a'] ++;

            if (diff == 0) ans.add(i + 1);
        }
        return ans;
    }

    // 461. 汉明距离
    public int hammingDistance(int x, int y) {
        int s = x ^ y, ret = 0;
        while (s != 0) {
            ret += s & 1;
            s >>= 1;
        }
        return ret;
    }

    // 494. 目标和
    int count = 0;
    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return -1;
    }

    private void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target)
                count ++;
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }

    // 动态规划
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) return 0;
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        for (int i = 1; i <= n; i ++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }

    // 538. 把二叉搜索树转换为累加树
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    // 543. 二叉树的直径
    int ans1;
    public int diameterOfBinaryTree(TreeNode root) {
        ans1 = 1;
        depth(root);
        return ans1 - 1;
    }

    private int depth(TreeNode node) {
        if (node == null) return 0;
        int L = depth(node.left);
        int R = depth(node.right);
        ans1 = Math.max(ans1, L + R + 1);
        return Math.max(L, R) + 1;
    }
    // 560. 和为 K 的子数组
    // 枚举
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end >= 0; end --) {
                sum += nums[end];
                if (sum == k) count ++;
            }
        }
        return count;
    }
    // 方法二：前缀和+哈希表优化
    public int subarraySum2(int[] nums, int k) {
        int count = 0, pre = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (map.containsKey(pre - k))
                count += map.get(pre - k);
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
    // 581. 最短无序连续子数组
    // 同理minn在i=4和1都会计算一次，以最后一次1为准
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = 0; i < n; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }

    // 617. 合并二叉树
    // 深度优先遍历
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees(t1.left, t2.left);
        merged.right = mergeTrees(t1.right, t2.right);
        return merged;
    }
    // 广度优先遍历
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode merged = new TreeNode(t1.val + t2.val);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
        queue.offer(merged);
        queue1.offer(t1);
        queue2.offer(t2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node = queue.poll(), node1 = queue1.poll(), node2 = queue2.poll();
            TreeNode left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
            if (left1 != null || left2 != null) {
                if (left1 != null && left2 != null) {
                    TreeNode left = new TreeNode(left1.val + left2.val);
                    node.left = left;
                    queue.offer(left);
                    queue1.offer(left1);
                    queue2.offer(left2);
                } else if (left1 != null) {
                    // 不需要再往队列里面塞值了
                    node.left = left1;
                } else if (left2 != null) {
                    node.left = left2;
                }
            }
            if (right1 != null || right2 != null) {
                if (right1 != null && right2 != null) {
                    TreeNode right = new TreeNode(right1.val + right2.val);
                    node.right = right;
                    queue.offer(right);
                    queue1.offer(right1);
                    queue2.offer(right2);
                } else if (right1 != null) {
                    node.right = right1;
                } else {
                    node.right = right2;
                }
            }
        }
        return null;
    }

    // 647. 回文子串
    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            int L = i / 2, R = i / 2 + i % 2;
            while (L >= 0 && R < n && s.charAt(L) == s.charAt(R)) {
                --L;
                ++R;
                ++ans;
            }
        }
        return ans;
    }

    // 739. 每日温度
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }



}
