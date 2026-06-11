package dsa.arrays.minOperation;

import java.util.Arrays;

public class MinCostToMakeArrayEqual {

    public static void main(String[] args) {

        int[] arr = {1,3,5,2};
        int[] cost  = {2,3,1,14};

        System.out.println(minCost(arr, cost));
    }

    private static long minCost(int[] nums, int[] cost) {

        int n = nums.length;

        // 1. Tie nums and costs together in a 2D array
        int[][] numAndCost = new int[n][2];
        long totalCostSum = 0;

        for (int i = 0; i < n; i++) {
            numAndCost[i][0] = nums[i];
            numAndCost[i][1] = cost[i];
            totalCostSum += cost[i];
        }

        // 2. Sort the array based on the nums values
        Arrays.sort(numAndCost, (a, b) -> Integer.compare(a[0], b[0]));

        // 3. Find the weighted median
        long currentCostSum = 0;
        long target = 0;

        for (int i = 0; i < n; i++) {
            currentCostSum += numAndCost[i][1];
            // The tipping point is when we reach or cross half the total cost
            if (currentCostSum >= (totalCostSum + 1) / 2) {
                target = numAndCost[i][0];
                break;
            }
        }

        // 4. Calculate the final result using the weighted median target
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += (long) Math.abs(nums[i] - target) * cost[i];
        }

        return result;
    }
}
