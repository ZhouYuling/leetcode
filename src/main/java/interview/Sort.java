package interview;

public class Sort {

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

    public static void insertSort(int[] arr){
        int len = arr.length;
        for(int i = 1; i < len; i ++){
            int temp = arr[i];
            for(int j = i - 1; j >= 0; j --){
                if(arr[j] > temp){
                    arr[j + 1] = arr[j];
                }else{
                    break;
                }
                arr[j + 1] = temp;
            }
        }
    }

    public static void shellSort(int[] arr){
        int n = arr.length;
        int increment = n / 2;
        for(;increment > 0; increment /= 2){
            int i = increment;
            for(;i < arr.length; i ++){
                int temp = arr[i];
                int t = i - increment;
                while(t >= 0 && arr[t] > temp){
                    arr[i] = arr[t];
                    t -= increment;
                }
                arr[t + increment] = temp;
            }
        }
    }

    public static void mergeSort(int[] arr, int left, int right){
        if(left < right){
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid, right);
            merge(arr, left, right);
        }
    }

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

    public static void quickSort(int[] a, int l, int r){
        if(l < r){
            int i = l;
            int j = r;
            int x = a[i];
            while(i < j){
                while(i < j && a[j] > x){
                    j--;
                }
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

}
