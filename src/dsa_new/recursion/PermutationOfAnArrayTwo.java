package dsa_new.recursion;

import java.util.ArrayList;
import java.util.List;

public class PermutationOfAnArrayTwo {

    public static void main(String[] args) {

        int[] arr = {1,2,3};

        List<List<Integer>> res = new ArrayList<>();

        int n = 3;

        permutationTwo(0, arr, res, n);

        System.out.println(res);
    }

    private static void permutationTwo(int index, int[] arr, List<List<Integer>> res, int k) {

        if(index >= arr.length) {
            var list = new ArrayList<Integer>();
            for (int j : arr) list.add(j);

            res.add(list);
            if(res.size() == k) {
                System.out.println("kth permutation"+ list);
            }
            return;
        }

        for (int i=index; i<arr.length;i++) {
            swap(arr, index, i);
            permutationTwo(index+1, arr, res, k);
            swap(arr, index, i);
        }

    }

    static void swap (int[] arr, int m, int n) {
        int t = arr[m];
        arr[m] = arr[n];
        arr[n] = t;
    }
}
