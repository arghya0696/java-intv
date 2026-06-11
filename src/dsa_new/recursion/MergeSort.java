package dsa_new.recursion;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    public static void main(String[] args) {

        int[] arr = {3,2,4,1,3};

        mergeSort(arr, 0, arr.length-1);

        for (int i : arr) System.out.print(i+" ");
    }

    private static void mergeSort(int[] arr, int low, int high) {

        if(low == high)
            return;

        int mid = (low + high) /2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid+1,high);
        merge(arr, low, mid, high);
    }

    static void merge(int[] arr, int low, int mid, int high) {
        int i = low;
        int j = mid+1;

        List<Integer> res = new ArrayList<>();

        while (i<=mid && j<=high) {
           if(arr[i]<=arr[j]) {
               res.add(arr[i]);
               i++;
           } else {
               res.add(arr[j]);
               j++;
           }
        }

        // remaining
        while (i<=mid) {
            res.add(arr[i]);
            i++;
        }

        while (j<=high) {
            res.add(arr[j]);
            j++;
        }
        for(int k=low;k<=high;k++) {
            arr[k] = res.get(k - low);
        }

    }
}
