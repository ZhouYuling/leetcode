package offer;

import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class offer_41 {

    class MedianFinder {

        Queue<Integer> A,B;

        /** initialize your data structure here. */
        public MedianFinder() {
            A = new PriorityQueue<>();
            B = new PriorityQueue<>((a, b) -> b - a);
        }

        public void addNum(int num) {
            // 判断条件A和B可以对换位置，但是输出也应该跟着变
            if (A.size() != B.size()) {
                B.add(num);
                A.add(B.poll());
            } else {
                A.add(num);
                B.add(A.poll());
            }
        }

        public double findMedian() {
            if (A.isEmpty()) return B.peek();
            if (B.isEmpty()) return A.peek();
            return A.size() != B.size() ? B.peek() : (A.peek() + B.peek()) / 2.0;
        }
    }

}
