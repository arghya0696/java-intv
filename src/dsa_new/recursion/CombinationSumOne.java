package dsa_new.recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumOne {

    public static void main(String[] args) {

        List<List<Integer>> res = new ArrayList<>();
        int[] arr = {2,3,6,7};
        int target = 7;
        combinationSum(0, arr, target, res, new ArrayList<>());

        System.out.println(res);
    }

    public static void combinationSum(int index, int[] arr, int target, List<List<Integer>> res, List<Integer> list) {

        if(index >= arr.length) {
            if(target == 0) {
                res.add(new ArrayList<>(list));
            }
            return;
        }

        // take but don't increment as we can take same value multiple time
        if(arr[index] <= target) {
            list.add(arr[index]);
            combinationSum(index, arr, target - arr[index], res, list);
            list.removeLast(); // remove when come back for other comb.
        }

        // not take so def increase index
        combinationSum(index+1, arr, target, res, list);
    }
}
