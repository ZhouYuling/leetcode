package offerII;

public class offer_006 {

    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) return new int[]{i, mid};
                else if (numbers[mid] > target - numbers[i]) high = mid - 1;
                else low = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    public int[] twoSum2(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) return new int[]{low, high};
            else if(sum < target) ++ low;
            else -- high;
        }
        return new int[]{-1, -1};
    }

}
