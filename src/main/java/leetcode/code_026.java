package leetcode;

public class code_026 {

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     */
    public static void main(String[] args) {

        int[] nums = {0,1,1,2,3,3,4,4,5};
        int i = removeDuplicates(nums);
        System.out.println(i);

        System.out.println("=============");
        for (int num : nums
             ) {
            System.out.println(num);
        }

    }

    public static int removeDuplicates(int[] nums) {

        if (nums.length == 1){
            return 1;
        }

        int i = 0;
        for (int j = 1;j < nums.length; j ++){
            if(nums[i] != nums[j]){
                i ++;
                nums[i] = nums[j];
            }
        }

        System.out.println("==========");
        System.out.println(nums[nums.length - 1]);
        System.out.println("==========");

        return i + 1;
    }



}
