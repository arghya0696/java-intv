package dsa.recursion;

import java.util.ArrayList;
import java.util.List;

public class SubsetSumOne {

    static List<Integer> result = new ArrayList<>();
    public static void main(String[] args) {

        int[] arr = {3,2,1};

        temp(arr);
    }

    private static void temp(int[] arr) {
        
       subsetSumOne(arr, arr.length, new ArrayList<>(), 0);

        System.out.println("ans:"+ result.stream().sorted().toList());
    }

    private static void subsetSumOne(int[] arr, int length, ArrayList<Integer> data, int i) {

        if(i>=length) {
            if(data.isEmpty()) {
                result.add(0);
            }
            else
                result.add(data.stream().reduce(0, Integer::sum));
            return;
        }

        data.add(arr[i]);
        subsetSumOne(arr, length, data,i+1);

        data.removeLast();
        subsetSumOne(arr, length, data,i+1);
    }
}



