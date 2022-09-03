package interview;

import utils.Utils;

import java.util.*;

import static utils.Utils.swap;

public class Sort {

    //选择排序 每一次排序排序，把最小的一个数放在最前面
    public static void selectionSort(int[] array){
        int len = array.length;
        int min;
        for(int i = 0; i < len ; i ++){
            // 是下标，而不是对应的值
            min = i;
            for(int j = i; j < len; j ++){
                if(array[min] > array[j]){
                    min = j;
                }
            }
            swap(array, i, min);
        }
    }

    //冒泡排序 大的数依次和后面小的数交换位置
    public static void bubbleSort(int[] array){
        int len = array.length;
        for(int i = 0; i < len; i ++){
            // 注意边界，后面已经存在排好序的i个元素，不需要再次替换
            for(int j = 0; j < len - 1 - i; j ++){
                if(array[j + 1] < array[j]){
                    swap(array, j, j + 1);
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
            mergeSort(arr, mid + 1, right);
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
        // 链表相对于数组来说，有一个好处就是，只要修改指针，后面的元素就不需要遍历了
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
    public static void quickSort(int[] nums, int l, int r){
        if(l < r){
            int i = l,j = r;
            // 加上一个随机数，《算法导论》上说期望时间复杂度是O(n)
            // 空间复杂度期望为O(logn),最坏情况是O(n),每次划分都是最大值或最小值，需要递归调用n - 1层
            int random = new Random().nextInt(r - l + 1) + l;
            swap(nums, l, random);
            int mid = partition3(nums, i, j);
            quickSort(nums, l, mid - 1);
            quickSort(nums, mid + 1, r);
        }
    }

    private static int partition(int[] a, int i, int j) {
        int x = a[i];
        while(i < j){
            while(i < j && a[j] > x){
                j--;
            }
            //if判断可以省略掉，因为最坏的情况是i=j
            if(i < j){
                a[i++] = a[j];
            }
            while(i < j && a[i] < x){
                i++;
            }
            if(i < j){
                a[j--] = a[i];
            }
        }
        a[i] = x;
        return i;
    }

    private static int partition2(int[] nums, int lo, int hi) {
        int v = nums[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (++i <= hi && nums[i] < v);
            while (--j >= lo && nums[j] > v);
            if (i >= j) break;
            Utils.swap(nums, i, j);
        }
        nums[lo] = nums[j];
        nums[j] = v;

        return j;
    }

    // 以左一作为基准
    private static int partition3(int[] nums, int l, int r) {
        int pivot = nums[l];
        int i = r + 1;
        for (int j = r; j > 0; --j) {
            if (nums[j] >= pivot) {
                i = i - 1;
                Utils.swap(nums, i, j);
            }
        }
        Utils.swap(nums, i - 1, l);
        return i - 1;
    }

    // 以右一作为基准，只作为partition3的参考
    private static int partition4(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1; ++j) {
            if (nums[j] <= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
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
            if (i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex])
                maxIndex = i * 2 + 1;
            //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
            if (i * 2 + 2 < len && array[i * 2 + 2] > array[maxIndex])
                maxIndex = i * 2 + 2;
            //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
            if (maxIndex != i) {
                swap(array, maxIndex, i);
                i = maxIndex;
            }
        }
    }

    //手写冒泡排序 大的数依次和后面小的数交换位置
    public static void bubbleSort2(int[] array){
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j + 1] < array[j]) Utils.swap(array, j, j + 1);
            }
        }
    }

    //手写选择排序 每一次排序排序，把最小的一个数放在最前面
    public static void selectionSort2(int[] array){
        int len = array.length;
        int min = 0;
        for (int i = 0; i < len; i++) {
            min = i;
            for (int j = i; j < len; j++) {
                if (array[min] > array[j]) min = j;
            }
            swap(array, i, min);
        }
    }

    //手写插入排序 遍历把当前位置的数插入到已经排序好的位置上
    public static int[] insertSort2(int[] array) {
        int len = array.length;
        if (len == 0) return array;
        int current;
        for (int i = 0; i < len - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex --;
            }
            array[preIndex + 1] = current;
        }

        return array;
    }

    //手写归并排序
    public static void mergeSort2(int[] arr, int left, int right){
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort2(arr, left, mid);
            mergeSort2(arr, mid + 1, right);
            merge2(arr, left, mid, right);
        }
    }

    //数组进行合并
    public static void merge2(int[] arr, int left, int mid, int right){
        int[] tmp = new int[arr.length];
        int k = left;
        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) tmp[k ++] = arr[i ++];
            else tmp[k ++] = arr[j ++];
        }
        while (i <= mid) tmp[k ++] = arr[i ++];
        while (j <= right) tmp[k ++] = arr[j ++];
        int t = left;
        while (t <= right) arr[t] = tmp[t ++];
    }

    //手写希尔排序 是插入排序的改进版本
    public static void shellSort2(int[] arr){
        int n = arr.length;
        //希尔增量增量
        for(int increment = n / 2;increment > 0; increment /= 2){
            // 以下是插入排序
            for(int i = increment;i < arr.length; i ++){
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

    // 猴子排序
    Random random = new Random();

    private void bogoSort(int[] arr) {
        while (true) {
            if (isAscendingSorted(arr)) break;
            shuffle(arr);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int swapPosition = random.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[swapPosition];
            arr[swapPosition] = temp;
        }
    }

    private boolean isAscendingSorted(int[] arr) {
        if (arr == null || arr.length < 2) return true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {

        Sort sort = new Sort();
        int[] nums = {9, 2, 4, 3, 5, 8, 7, 1, 6};
        // 测试使用左侧作为快排的边界
        int i = partition3(nums, 0, nums.length - 1);
        System.out.println(i);

        // 快排
        sort.quickSort(nums, 0, nums.length - 1);

        nums = new int[]{9, 2, 4, 3, 5, 8, 7, 1, 6};
        bubbleSort2(nums);

        nums = new int[]{9, 2, 4, 3, 5, 8, 7, 1, 6};
        selectionSort2(nums);

        nums = new int[]{9, 2, 4, 3, 5, 8, 7, 1, 6};
        insertSort(nums);

        nums = new int[]{9, 2, 4, 3, 5, 8, 7, 1, 6, 0};
        mergeSort(nums, 0, nums.length - 1);

        System.out.println(Arrays.toString(nums));

//        nums = new int[]{9, 2, 4, 3, 5, 8, 7, 1, 6, 0};
//        sort.bogoSort(nums);
//        System.out.println("猴子排序");
//        System.out.println(Arrays.toString(nums));
        Arrays.sort(nums);
        Collections.sort(new ArrayList<Integer>());

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1, 100);

        HashMap<String, String> map = new HashMap<>();
        map.put("", "");
        map.put(null, null);
        map.put(null, null);
        map.replace("", "1");


    }


}
