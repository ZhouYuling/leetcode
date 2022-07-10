package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class code_253 {

    // 优先队列
    class Solution {
        public int minMeetingRooms(int[][] intervals) {

            // 边界，会议为空，则返回0
            if (intervals.length == 0) {
                return 0;
            }

            // 根据起始时间排序
            Arrays.sort(
                    intervals,
                    new Comparator<int[]>() {
                        public int compare(final int[] a, final int[] b) {
                            return a[0] - b[0];
                        }
                    });

            // 最小堆，根据会议时间进行排序建堆
            PriorityQueue<Integer> allocator =
                    new PriorityQueue<Integer>(
                            intervals.length,
                            new Comparator<Integer>() {
                                public int compare(Integer a, Integer b) {
                                    return a - b;
                                }
                            });

            // 添加第一个排序时间
            allocator.add(intervals[0][1]);

            // 迭代余下的会议
            for (int i = 1; i < intervals.length; i++) {

                // 如果目前会议起始时间大于，目前最早结束的会议时间，则弹出堆顶元素
                // 这个房间能够继续使用
                if (intervals[i][0] >= allocator.peek()) {
                    allocator.poll();
                }

                // 把当前的会议添加到堆中
                allocator.add(intervals[i][1]);
            }

            // 返回所需会议室的最小数量
            return allocator.size();
        }
    }


    // 有序化
    class Solution2 {
        public int minMeetingRooms(int[][] intervals) {

            // 边界，会议为空，则返回0
            if (intervals.length == 0) {
                return 0;
            }

            Integer[] start = new Integer[intervals.length];
            Integer[] end = new Integer[intervals.length];

            for (int i = 0; i < intervals.length; i++) {
                start[i] = intervals[i][0];
                end[i] = intervals[i][1];
            }

            // 从小到大排序终止时间
            Arrays.sort(
                    end,
                    new Comparator<Integer>() {
                        public int compare(Integer a, Integer b) {
                            return a - b;
                        }
                    });

            // 从小到大排序起始时间
            Arrays.sort(
                    start,
                    new Comparator<Integer>() {
                        public int compare(Integer a, Integer b) {
                            return a - b;
                        }
                    });

            int startPointer = 0, endPointer = 0;

            int usedRooms = 0;

            while (startPointer < intervals.length) {

                // 会议起始时间大于另外一个会议的结束时间，该房间能够被继续使用
                if (start[startPointer] >= end[endPointer]) {
                    usedRooms -= 1;
                    endPointer += 1;
                }

                // We do this irrespective of whether a room frees up or not.
                // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
                // remain the same in that case. If no room was free, then this would increase used_rooms
                usedRooms += 1;
                startPointer += 1;

            }

            return usedRooms;
        }
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new int[0]));

    }

}
