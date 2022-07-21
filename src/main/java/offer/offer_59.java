package offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class offer_59 {

    class MaxQueue {
        Queue<Integer> q;
        Deque<Integer> d;

        public MaxQueue() {
            q = new LinkedList<>();
            d = new LinkedList<>();
        }

        public int max_value() {
            if (d.isEmpty()) return -1;
            return d.peekFirst();
        }

        public void push_back(int value) {
            // 注意，这个没有等于号，也就是说最大值可以重复，符合题意
            while (!d.isEmpty() && d.peekFirst() < value) d.pollFirst();
            d.offerLast(value);
            q.offer(value);
        }

        public int pop_front() {
            if (q.isEmpty()) return -1;
            int ans = q.poll();
            if (ans == d.peekFirst()) d.pollFirst();
            return ans;
        }
    }

    public static void main(String[] args) {

        offer_59 code = new offer_59();
        MaxQueue2 maxQueue = code.new MaxQueue2();
        maxQueue.push_back(2);
        maxQueue.push_back(3);
        maxQueue.push_back(2);
        maxQueue.push_back(2);
        maxQueue.push_back(2);
        maxQueue.pop_front();
        System.out.println(maxQueue.max_value());
        maxQueue.pop_front();
        System.out.println(maxQueue.max_value());
        maxQueue.pop_front();
        System.out.println(maxQueue.max_value());
        maxQueue.pop_front();
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());

        System.out.println("---------");

        maxQueue = code.new MaxQueue2();
        maxQueue.push_back(1);
        maxQueue.push_back(2);
        System.out.println(maxQueue.max_value());
        maxQueue.pop_front();
        System.out.println(maxQueue.max_value());
    }

    // 使用最小栈行不通，需要和存储数据的容器保持一直：
    // 队列的最大/小值，那么就需要维护一个单调队列
    // 栈的最大/小值，那么久需要维护一个单调栈
    class MaxQueue2 {
        Queue<Integer> q;
        Deque<Integer> minStack;

        public MaxQueue2() {
            q = new LinkedList<>();
            minStack = new LinkedList<>();
        }

        public int max_value() {
            if (minStack.isEmpty()) return -1;
            return minStack.peek();
        }

        public void push_back(int value) {
            if (minStack.size() == 0) minStack.push(value);
            else minStack.push(Math.max(minStack.peek(), value));
            q.offer(value);
        }

        public int pop_front() {
            if (q.isEmpty()) return -1;
            int ans = q.poll();
            minStack.poll();
            return ans;
        }
    }



}
