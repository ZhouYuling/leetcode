package offer;

public class offer_03 {

    static class Solution {
        public int findRepeatNumber(int[] nums) {
            int temp;
            for(int i=0;i<nums.length;i++){
                while (nums[i]!=i){
                    if(nums[i]==nums[nums[i]]){
                        return nums[i];
                    }
                    temp=nums[i];
                    nums[i]=nums[temp];
                    nums[temp]=temp;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 4, 5};
        int repeatNumber = solution.findRepeatNumber(nums);
        System.out.println(repeatNumber);


    }

}
