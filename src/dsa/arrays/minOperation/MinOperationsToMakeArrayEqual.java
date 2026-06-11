package dsa.arrays.minOperation;

// https://leetcode.com/problems/minimum-operations-to-make-array-equal/description/
public class MinOperationsToMakeArrayEqual {

    public static void main(String[] args) {

        System.out.println(minOperations(7));

    }

    public static int minOperations(int n) {

        int[] arr = new int[n];

        for(int i=0;i<n;i++) {
            arr[i] = (i*2)+1;
        }

        // find mean
        int mean;
        if(arr.length%2 == 1) {
            mean = arr[n/2];
        } else {
            mean = (arr[n/2] + arr[n/2-1])/2;
        }
        int numOfOp = 0;

        for(int i=0; i<n/2;i++) {
            numOfOp+=mean - arr[i];
        }

        return numOfOp;
    }
}
