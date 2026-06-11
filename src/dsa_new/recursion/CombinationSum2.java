package dsa_new.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {

    public static void main(String[] args) {

        List<List<Integer>> res = new ArrayList<>();
        int[] arr = {1,1,1,2,2};
        int target = 4;
        Arrays.sort(arr); // sort it first to do -> take the first one and leave the repetation and no need to take set to store

        //combinationSum2(0,target, arr,res, new ArrayList<>());

        int k = 2, newTarget = 18;
        int[] temp = new int[9];

        for(int i=0;i<9;i++)
            temp[i] = i+1;

//        for(int i=0;i<n;i++)
//            System.out.print(temp[i] +" ");
        System.out.println();
        combinationSum3(0,newTarget, temp,res, new ArrayList<>(), k);

        System.out.println(res);
    }

    private static void combinationSum2(int index, int target, int[] arr, List<List<Integer>> res, ArrayList<Integer> list) {

        if(target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i= index; i<arr.length;i++) {
            if(i>index && arr[i] == arr[i-1]) continue; // take the first one and leave the repetation

            if(arr[i] > target) break; // if current element is greater than target no need further recursion call

            list.add(arr[i]);
            combinationSum2(i+1, target - arr[i], arr, res, list);
            list.removeLast();
        }
    }

    private static void combinationSum3(int index, int target, int[] arr, List<List<Integer>> res, ArrayList<Integer> list, int k) {

        if(target == 0) {
            if(list.size() == k)
                res.add(new ArrayList<>(list));
            return;
        }

        for (int i= index; i<arr.length;i++) {
            if(i>index && arr[i] == arr[i-1]) continue; // take the first one and leave the repetation

            if(arr[i] > target) break; // if current element is greater than target no need further recursion call

            list.add(arr[i]);
            combinationSum3(i+1, target - arr[i], arr, res, list, k);
            list.removeLast();
        }
    }
}
