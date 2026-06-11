package dsa_new.recursion;

import java.util.ArrayList;
import java.util.List;

public class AllSubSequence {

    static int counter = 0;
    public static void main(String[] args) {

        int[] arr = {3,1,2};

//        printSubSequence(0, new ArrayList<>(), arr);
//
//        System.out.println("count: "+ counter);

        List<List<Integer>> ans = new ArrayList<>();

        //returnSubsequence(0, arr, new ArrayList<>(), ans, 0);

        //System.out.println(ans);
        List<Integer> res = new ArrayList<>();
        int sum  = 0;

        calculateSubsetSum(0, arr, sum, res);

        System.out.println(res);
    }

    private static void printSubSequence(int index, ArrayList<Integer> list, int[] arr) {

        if(index >= arr.length) {
            counter++;
            System.out.println(list);
            return;
        }

        list.add(arr[index]); // take
        printSubSequence(index+1, list, arr);

        list.removeLast(); // not take
        printSubSequence(index+1, list, arr);

    }

    public static void returnSubsequence(int index, int[] arr, ArrayList<Integer> list, List<List<Integer>> ans, int sum) {
        if(index >= arr.length) {
            ans.add(new ArrayList<>(list));
            System.out.println("sum::"+ sum);
            return;
        }

        sum+=arr[index];
        list.add(arr[index]);
        returnSubsequence(index+1, arr, list, ans, sum);

        list.removeLast();
        sum-=arr[index];
        returnSubsequence(index+1, arr, list, ans, sum);

    }

    public static void calculateSubsetSum(int index, int[] nums, int sum, List<Integer> list) {

        if(index >= nums.length) {
            list.add(sum);
            return;
        }
        sum+=nums[index];
        calculateSubsetSum(index+1, nums, sum, list);

        sum-=nums[index];
        calculateSubsetSum(index+1, nums, sum, list);
    }
}
