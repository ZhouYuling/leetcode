package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class code_346 {

    class MovingAverage {

        int sum = 0;
        int size = 0;
        Queue<Integer> queue = new LinkedList<>();

        public MovingAverage(int size) {
            this.size = size;
        }

        public double next(int val) {
            if (queue.size() >= size) sum -= queue.poll();
            sum += val;
            queue.offer(val);
            return sum * 1.0 / Math.min(queue.size(), size);
        }
    }

    class MovingAverage2 {

        int size,head = 0,windowSum = 0, count = 0;
        int[] queue;

        public MovingAverage2(int size) {
            this.size = size;
            queue = new int[size];
        }

        public double next(int val) {
            count ++;
            int tail = (head + 1) % size;
            windowSum = windowSum - queue[tail] + val;
            head = (head + 1) % size;
            queue[head] = val;
            return windowSum * 1.0 / Math.min(count, size);
        }
    }

}
