package leetcode;

public class code_011 {

    public class Solution {
        public int maxArea(int[] height) {
            int left = 0,right = height.length - 1;
            int maxArea = 0;
            while (left < right) {
                // left和right中间的区域，不包括left，所以不用+1
                int area = Math.min(height[left], height[right]) * (right - left);
                maxArea = Math.max(maxArea, area);
                if (height[left] < height[right]) {
                    left ++;
                } else {
                    right --;
                }
            }
            return maxArea;
        }
    }

}
