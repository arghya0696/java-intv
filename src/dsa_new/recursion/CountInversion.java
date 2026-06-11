package dsa_new.recursion;

import java.util.ArrayList;
import java.util.List;

public class CountInversion {

    static int count = 0;

    public static void main(String[] args) {

        int[] arr = {5,3,2,4,1};

        countInversion(arr, 0, arr.length-1);

        System.out.println(count);

        for (int i : arr) System.out.print(i+" ");
    }

    private static void countInversion(int[] arr, int low, int high) {

        if(low >=high)
            return;

        int mid = (low+high)/2;
        countInversion(arr, low, mid);
        countInversion(arr, mid+1, high);
        merge(arr, low, mid, high);
    }

    static void merge(int[] arr, int low, int mid, int high) {
        List<Integer> res = new ArrayList<>();

        int l = low;
        int r = mid+1;

        while (l<=mid && r<=high) {
            if(arr[l]<=arr[r]) {
                res.add(arr[l]);
                l++;
            } else {
                count = count + (mid-l+1);
                res.add(arr[r]);
                r++;
            }
        }
        while (l<=mid) {
            res.add(arr[l]);
            l++;
        }

        while (r<=high) {
            res.add(arr[r]);
            r++;
        }

        for (int k = low;k<=high;k++) {
            arr[k] = res.get(k - low);
        }
    }
}
