package dsa.recursion;

import java.util.ArrayList;

public class CombinationSum1 {

    public static void main(String[] args) {

        int[] arr = {2,3,6,7};
        int target  = 7;


        funcComb(0, arr, arr.length, target, new ArrayList<>());
    }

    private static void funcComb(int i, int[] arr, int n, int target, ArrayList<Integer> res) {

        if(i==n) {
            if(target == 0)
                System.out.println(res);
            return;
        }

        if(arr[i]<=target) {
            res.add(arr[i]);
            funcComb(i, arr, n, target - arr[i], res);
            res.removeLast();
        }
        funcComb(i+1, arr, n, target, res);
    }
}
