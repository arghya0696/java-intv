package dsa.arrays.minOperation;

// https://leetcode.com/problems/minimum-operations-to-make-array-equal-to-target/description/
// solt : https://www.youtube.com/watch?v=yX6NHGF_YQ4&t=2518s
// similar : https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/description/
public class MinOperationToMakeArrayToTarget {

    public static void main(String[] args) {

        int[] nums = {3,5,1,2}, target = {4,6,2,4};

        System.out.println(minimumOperations(nums, target));
    }

    private static long minimumOperations(int[] nums, int[] target) {

        int[] diff = new int[nums.length];

        for(int i=0;i<nums.length;i++) {
            diff[i] = target[i] - nums[i];
        }

        long res = 0, prev = 0, cur;
        for(int i=0;i<diff.length;i++) {
            cur = diff[i];

            if((cur> 0 && prev<0) || (cur<0 && prev>0)) {
                res+=Math.abs(cur);
            } else if (Math.abs(cur) > Math.abs(prev)) {
                res+= (Math.abs(cur)-Math.abs(prev));
            }
            prev = cur;
        }

        return res;
    }
}
