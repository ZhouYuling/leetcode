package interview;

import java.util.ArrayList;

import static utils.Utils.swap;

public class Sort {

    //选择排序 每一次排序排序，把最小的一个数放在最前面
    public static void selectionSort(int[] array){
        int len = array.length;
        int min;
        for(int i = 0; i < len ; i ++){
            min = i;
            for(int j = i; j < len; j ++){
                if(array[min] > array[j]){
                    min = j;
                }
            }
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }

    //冒泡排序 大的数依次和后面小的数交换位置
    public static void bubbleSort(int[] array){
        int len = array.length;
        for(int i = 0; i < len; i ++){
            for(int j = 0; j < len - 1 - i; j ++){
                if(array[j + 1] < array[j]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    //插入排序 遍历把当前位置的数插入到已经排序好的位置上
    public static int[] insertSort(int[] array) {
        if (array.length == 0)
            return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    //希尔排序 是插入排序的改进版本
    public static void shellSort(int[] arr){
        int n = arr.length;
        //希尔增量增量
        int increment = n / 2;
        for(;increment > 0; increment /= 2){
            int i = increment;
            for(;i < arr.length; i ++){
                int temp = arr[i];
                //每一组内进行排序
                int t = i - increment;
                while(t >= 0 && arr[t] > temp){
                    arr[i] = arr[t];
                    t -= increment;
                }
                arr[t + increment] = temp;
            }
        }
    }

    //归并排序
    public static void mergeSort(int[] arr, int left, int right){
        if(left < right){
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid, right);
            merge(arr, left, right);
        }
    }

    //数组进行合并
    public static void merge(int[] arr, int left, int right){
        int mid = (left + right) / 2;
        int[] tmp = new int[arr.length];
        int k = left;
        int i = left;
        int j = mid + 1;
        while(i <= mid && j <= right){
            if(arr[i] < arr[j]){
                tmp[k++] = arr[i++];
            }else{
                tmp[k++] = arr[j++];
            }
        }
        while(i <= mid){
            tmp[k++] = arr[i++];
        }
        while(j <= right){
            tmp[k++] = arr[j++];
        }
        int t = left;
        while(t <= right){
            arr[t] = tmp[t++];
        }
    }

    //快速排序
    public static void quickSort(int[] a, int l, int r){
        if(l < r){
            int i = l;
            int j = r;
            int x = a[i];
            while(i < j){
                while(i < j && a[j] > x){
                    j--;
                }
                //if判断可以省略掉，因为最坏的情况是i=j
                if(i < j){
                    a[i ++] = a[j];
                }
                while(i < j && a[i] < x){
                    i ++;
                }
                if(i < j){
                    a[j--] = a[i];
                }
            }
            a[i] = x;
            quickSort(a, l, i - 1);
            quickSort(a, l + 1, r);
        }
    }

    //基数排序
    public static int[] RadixSort(int[] array) {
        if (array == null || array.length < 2)
            return array;
        // 1.先算出最大数的位数；
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        int mod = 10, div = 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++)
            bucketList.add(new ArrayList<Integer>());
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / div;
                bucketList.get(num).add(array[j]);
            }
            int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++)
                    array[index++] = bucketList.get(j).get(k);
                bucketList.get(j).clear();
            }
        }
        return array;
    }


    //声明全局变量，用于记录数组array的长度；
    private static int len;
    // 堆排序算法

    public static int[] HeapSort(int[] array) {
        len = array.length;
        if (len < 1) return array;
        //1.构建一个最大堆
        buildMaxHeap(array);
        //2.循环将堆首位（最大值）与末位交换，然后在重新调整最大堆
        while (len > 0) {
            swap(array, 0, len - 1);
            len--;
            adjustHeap(array, 0);
        }
        return array;
    }

    // 建立最大堆
    private static void buildMaxHeap(int[] array) {
        //从最后一个非叶子节点开始向上构造最大堆
        for (int i = (len/2 - 1); i >= 0; i--) { //感谢 @让我发会呆 网友的提醒，此处应该为 i = (len/2 - 1)
            adjustHeap(array, i);
        }
    }

    // 调整使之成为最大堆
    private static void adjustHeap(int[] array, int i) {
        int maxIndex = i,l = i * 2 + 1,r = i * 2 + 2;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if (l < len && array[l] > array[maxIndex])
            maxIndex = l;
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if (r < len && array[r] > array[maxIndex])
            maxIndex = r;
        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
        if (maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(array, maxIndex);
        }
    }

    //下面这种写法和上面的效果是一致的
    private static void adjustHeap2(int[] array, int i) {
        while (true) {
            int maxIndex = i;
            //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
            if (i * 2 < len && array[i * 2] > array[maxIndex])
                maxIndex = i * 2;
            //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
            if (i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex])
                maxIndex = i * 2 + 1;
            //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
            if (maxIndex != i) {
                swap(array, maxIndex, i);
                i = maxIndex;
            }
        }
    }

}
