package dsa_new.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PermutationOfAnArray {

    public static void main(String[] args) {

        int[] arr = {1,2,3};

        List<List<Integer>> res = new ArrayList<>();

        generatePermutation(arr, new ArrayList<Integer>(), res, new HashMap<Integer, Boolean>());

        System.out.println(res);
    }

    private static void generatePermutation(int[] arr, ArrayList<Integer> list, List<List<Integer>> res, HashMap<Integer, Boolean> map) {

        if(list.size() == arr.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i= 0; i<arr.length; i++) {
            if(map.get(i) == null || map.get(i).equals(Boolean.FALSE)) {
                list.add(arr[i]);
                map.put(i, Boolean.TRUE);
                generatePermutation(arr, list, res, map);
                list.removeLast();
                map.put(i, Boolean.FALSE);
            }
        }
    }
}
