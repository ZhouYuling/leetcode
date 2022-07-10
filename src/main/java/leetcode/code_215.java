package leetcode;

import java.util.Random;

public class code_215 {

    //基于快速排序的选择方法
    class Solution1 {
        Random random = new Random();

        //程序入口 nums.length - k 表示前有多少第 nums.length - k 小的元素
        public int findKthLargest(int[] nums, int k) {
            return quickSelect(nums, 0, nums.length - 1, nums.length - k);
        }

        public int quickSelect(int[] a, int l, int r, int index) {
            int q = randomPartition(a, l, r);
            if (q == index) {
                return a[q];
            } else {
                return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index); //判断是否
            }
        }

        public int randomPartition(int[] a, int l, int r) {
            int i = random.nextInt(r - l + 1) + l;
            swap(a, i, r);
            return partition(a, l, r);
        }

        public int partition(int[] a, int l, int r) {
            int x = a[r], i = l - 1;
            for (int j = l; j < r; ++j) {
                if (a[j] <= x) {
                    swap(a, ++i, j);
                }
            }
            swap(a, i + 1, r);
            return i + 1;
        }
    }

     class Solution {

        public int findKthLargest(int[] nums, int k) {
            int heapSize = nums.length;
            buildMaxHeap(nums, heapSize);
            for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
                swap(nums, 0, i);
                --heapSize;
                maxHeapify(nums, 0, heapSize);
            }
            return nums[0];
        }

        public void buildMaxHeap(int[] a, int heapSize) {
            for (int i = heapSize / 2; i >= 0; --i) {
                maxHeapify(a, i, heapSize);
            }
        }

        public void maxHeapify(int[] a, int i, int heapSize) {
            int l = i * 2 + 1, r = i * 2 + 2, largest = i;
            if (l < heapSize && a[l] > a[largest]) {
                largest = l;
            }
            if (r < heapSize && a[r] > a[largest]) {
                largest = r;
            }
            if (largest != i) {
                swap(a, i, largest);
                maxHeapify(a, largest, heapSize);
            }
        }

    }

    //数组a，下标i和下表j进行对调
    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {

        code_215 code = new code_215();
        Solution1 solution1 = code.new Solution1();
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        int kthLargest = solution1.findKthLargest(nums, k);
        System.out.println(kthLargest);


    }


    //快排简单方法
    class Solution3 {
        Random rand=new Random();
        public int findKthLargest(int[] nums, int k) {
            return quickSort(nums,k,0,nums.length-1);
        }
        private int quickSort(int[] nums,int k,int left,int right){
            int index=rand.nextInt(right-left+1)+left;
            int flag=nums[index];
            nums[index]=nums[left];
            int i=left,j=right;
            while (i<j){
                while (i<j&&nums[j]<=flag) j--;
                nums[i]=nums[j];
                while (i<j&&nums[i]>=flag) i++;
                nums[j]=nums[i];
            }
            nums[i]=flag;
            if (i==k-1) return nums[i];
            else if (i<k-1) return quickSort(nums,k,i+1,right);
            else return quickSort(nums,k,left,i-1);
        }
    }

}
