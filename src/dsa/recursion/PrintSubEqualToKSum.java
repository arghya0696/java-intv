package dsa.recursion;

import java.util.ArrayList;

public class PrintSubEqualToKSum {

    static int k = 5;

    public static void main(String[] args) {

        int[] arr = {1,2,1,3};

        fun(arr,arr.length,0,0, new ArrayList<>());
    }

    private static void fun(int[] arr, int length, int i, int sum, ArrayList<Integer> data) {

        if(i>=length) {
            if(sum == k)
                System.out.println(data);
            return;
        }
        // sum+=arr[i];
        data.add(arr[i]);
        fun(arr, arr.length,i+1, sum+arr[i], data);

        //sum-=arr[i];
        data.removeLast();
        fun(arr, arr.length,i+1, sum, data);

    }
}
