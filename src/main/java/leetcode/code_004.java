package leetcode;

public class code_004 {

    //暴力法
    class Solution {
        public double findMedianSortedArrays(int[] A, int[] B) {
            //两个数组长度
            int m = A.length;
            int n = B.length;
            //总长度
            int len = m + n;
            int left = -1, right = -1;
            int aStart = 0, bStart = 0;
            //注意边界是 <=
            for (int i = 0; i <= len / 2; i++) {
                left = right;
                //A指针没超过范围，b指针超过范围||A这个时候的值小于B
                if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                    right = A[aStart++];
                } else {
                    right = B[bStart++];
                }
            }
            //二进制最后一位为0 判断结果成立，则为偶数
            if ((len & 1) == 0)
                return (left + right) / 2.0;
            else
                return right;
        }

    }

    //二分查找
    class Solution2 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n = nums1.length;
            int m = nums2.length;
            int left = (n + m + 1) / 2;
            int right = (n + m + 2) / 2;
            return (
                    getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) +
                            getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)
            ) * 0.5;
        }

        private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
            int len1 = end1 - start1 + 1;
            int len2 = end2 - start2 + 1;
            //注意这个地方
            if(len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
            if(len1 == 0) return nums2[start2 + k - 1];

            if(k == 1) return Math.min(nums1[start1], nums2[start2]);

            int i = start1 + Math.min(len1, k / 2) - 1;
            int j = start2 + Math.min(len2, k / 2) - 1;

            if(nums1[i] > nums2[j]){
                return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
            }else {
                return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
            }
        }
    }

    // 划分数组
    class Solution3 {
        public double findMedianSortedArrays(int[] A, int[] B) {

            int m = A.length;
            int n = B.length;
            if(m > n) {
                return findMedianSortedArrays(B, A);
            }
            int iMin = 0, iMax = m;
            while (iMin <= iMax) {
                int i = (iMin + iMax) / 2;
                int j = (m + n + 1) / 2 - i;
                if (j != 0 && i != m && B[j - 1] > A[i]) {
                    iMin = i + 1;
                }
                else if (i != 0 && j != n && A[i - 1] > B[j]) {
                    iMax = i - 1;
                }
                else {
                    int maxLeft = 0;
                    if(i == 0) {maxLeft = B[j - 1];}
                    else if (j == 0) {maxLeft= A[i - 1];}
                    else {maxLeft = Math.max(A[i-1], B[j - 1]);}
                    if((m + n) % 2 == 1) {return maxLeft;}

                    int minRight = 0;
                    if (i == m) {minRight=B[j];}
                    else if(j == n){minRight = A[i];}
                    else {minRight = Math.min(B[j], A[i]);}

                    return (maxLeft + minRight) / 2.0;
                }
            }
            return 0.0;
        }

    }

    public static void main(String[] args) {

        int len = 3;
        System.out.println(len & 1);

    }

}
