package leetcode;

import java.util.*;

public class code_350 {

    // 哈希表
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums2.length > nums1.length) return intersect(nums2, nums1);

        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num:
             nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num:
             nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                res.add(num);
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0) map.remove(num);
            }
        }
        int[] ans = new int[res.size()];
        int index = 0;
        for(int r : res) {
            ans[index ++] = r;
        }
        return ans;
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums2.length > nums1.length) return intersect(nums2, nums1);
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int idx1 = 0, idx2 = 0;
        int[] res = new int[nums1.length];
        int index = 0;
        while (idx1 < nums1.length && idx2 < nums2.length) {
            if (nums1[idx1] < nums2[idx2]) idx1 ++;
            else if (nums1[idx1] > nums2[idx2]) idx2 ++;
            else {
                res[index ++] = nums1[idx1];
                idx1 ++;
                idx2 ++;
            }
        }
        return Arrays.copyOfRange(res, 0, index);
    }

}
