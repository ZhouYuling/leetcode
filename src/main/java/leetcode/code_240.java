package leetcode;

public class code_240 {

    static class Solution {
        private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
            int lo = start;
            int hi = vertical ? matrix[0].length-1 : matrix.length-1;

            while (hi >= lo) {
                int mid = (lo + hi)/2;
                if (vertical) {
                    // 搜索列
                    if (matrix[start][mid] < target) {
                        lo = mid + 1;
                    } else if (matrix[start][mid] > target) {
                        hi = mid - 1;
                    } else {
                        return true;
                    }
                } else {
                    // 搜索行
                    if (matrix[mid][start] < target) {
                        lo = mid + 1;
                    } else if (matrix[mid][start] > target) {
                        hi = mid - 1;
                    } else {
                        return true;
                    }
                }
            }

            return false;
        }

        public boolean searchMatrix(int[][] matrix, int target) {
            // an empty matrix obviously does not contain `target`
            if (matrix == null || matrix.length == 0) {
                return false;
            }

            // iterate over matrix diagonals
            int shorterDim = Math.min(matrix.length, matrix[0].length);
            for (int i = 0; i < shorterDim; i++) {
                boolean verticalFound = binarySearch(matrix, target, i, true);
                boolean horizontalFound = binarySearch(matrix, target, i, false);
                if (verticalFound || horizontalFound) {
                    return true;
                }
            }

            return false;
        }
    }

    //暴力搜索
    class Solution1 {
        public boolean searchMatrix(int[][] matrix, int target) {
            //遍歷行
            for (int[] row : matrix) {
                //遍历列
                for (int element : row) {
                    if (element == target) {
                        return true;
                    }
                }
            }
            return false;
        }
    }


    //二分查找
    class Solution2 {
        //入口
        public boolean searchMatrix(int[][] matrix, int target) {
            for (int[] row : matrix) {
                //改行二分查找
                int index = search(row, target);
                if (index >= 0) {
                    return true;
                }
            }
            return false;
        }

        public int search(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            //循环退出条件
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                int num = nums[mid];
                if (num == target) {
                    return mid;
                } else if (num > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return -1;
        }
    }

    //z字形查找
    class Solution3 {
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            int x = 0, y = n - 1;
            while (x < m && y >= 0) {
                if (matrix[x][y] == target) {
                    return true;
                }
                if (matrix[x][y] > target) {
                    --y;
                } else {
                    ++x;
                }
            }
            return false;
        }
    }

    // 这个Z型查找，从左下开始遍历是不是可以？
    class Solution4 {
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            int x = m - 1, y = 0;
            while (x >= 0 && y < n) {
                int cur = matrix[x][y];
                if (cur == target) {
                    return true;
                }
                if (cur > target) {
                    --x;
                } else {
                    ++y;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {

    }



}
