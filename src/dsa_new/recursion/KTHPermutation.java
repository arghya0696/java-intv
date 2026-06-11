package dsa_new.recursion;

import java.util.ArrayList;
import java.util.List;

public class KTHPermutation {

    public static void main(String[] args) {

        int k = 17, n = 4;

        StringBuilder str = new StringBuilder();
        for(int i=0;i<n;i++) {
            str.append(i+1);
        }

        System.out.println(kthPerm(str.toString(), n, k-1));

    }

    private static void perm(int index, int[] arr,List<List<Integer>> res) {

        if(index == arr.length) {
            var list = new ArrayList<Integer>();
            for (int j : arr) list.add(j);

            res.add(list);
        }
        for(int i=index;i<arr.length;i++) {
            swap(arr, i, index);
            perm(index+1, arr, res);
            swap(arr, i, index);
        }
    }

    static void swap (int[] arr, int m, int n) {
        int t = arr[m];
        arr[m] = arr[n];
        arr[n] = t;
    }

    private static String kthPerm(String str, int n ,int k) {
        StringBuilder ans = new StringBuilder();
        int len = str.length();
        int nextIndex;
        while (ans.length() != len) {
            n = n-1;
            int fact = factorial(n);
            nextIndex = k / fact;
            k = k%fact;
            ans.append(str.split("")[nextIndex]);
            var firstHalf = str.substring(0, nextIndex);
            var secondHalf = str.substring(nextIndex+1);
            str = firstHalf+secondHalf;
        }
        return ans.toString();
    }

    static int factorial(int n)
    {
        return (n == 1 || n == 0) ? 1
                : n * factorial(n - 1);
    }
}
