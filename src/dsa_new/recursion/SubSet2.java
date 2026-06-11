package dsa_new.recursion;

import java.util.ArrayList;
import java.util.List;

public class SubSet2 {

    public static void main(String[] args) {

        int[] arr = {1,2,2};

        List<List<Integer>> res = new ArrayList<>();

        subset2(0,arr,new ArrayList<>(), res);

        System.out.println(res);
    }

    private static void subset2(int index, int[] arr, ArrayList<Integer> list, List<List<Integer>> res) {

        res.add(new ArrayList<>(list));
        for (int i=index; i<arr.length;i++) {
            if(i>index && arr[i] == arr[i-1]) continue;

            list.add(arr[i]);
            subset2(i+1, arr, list, res);
            list.removeLast();
        }
    }
}
