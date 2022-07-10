package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class code_252 {

    class Solution {

        public boolean canAttendMeetings(int[][] intervals) {
            for (int i = 0; i < intervals.length; i++) {
                for (int j = i + 1; j < intervals.length; j++) {
                    if (overlap(intervals[i], intervals[j]))
                        return false;
                }
            }
            return true;
        }

        public boolean overlap(int[] i1, int[] i2) {
            return ((i1[0] >= i2[0] && i1[0] < i2[1]) || (i2[0] >= i1[0] && i2[0] < i1[1]));
        }
    }


    class Solution2 {

        public boolean canAttendMeetings(int[][] intervals) {
            Arrays.sort(intervals, new Comparator<int[]>() {
                public int compare(int[] i1, int[] i2) {
                    return i1[0] - i2[0];
                }
            });

            for (int i = 0; i < intervals.length - 1; i++) {
                if (intervals[i][1] > intervals[i + 1][0])
                    return false;
            }
            return true;
        }
    }


}
