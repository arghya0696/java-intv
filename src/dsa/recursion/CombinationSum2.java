package dsa.recursion;

import java.util.*;
import java.util.stream.Collectors;

public class CombinationSum2 {

    static Set<String> dpSet = new HashSet<>();

    public static void main(String[] args) {

        // basically make it unique set

        int[] arr = {10,1,2,7,6,1,5};
        int target  = 8;


        funcComb(0, arr, arr.length, target, new ArrayList<>());
    }

    private static void funcComb(int i, int[] arr, int n, int target, ArrayList<Integer> res) {

        if(i==n) {
            if(target == 0) {
                String key = res.stream().sorted().map(Object::toString).collect(Collectors.joining());
                if(!dpSet.contains(key))
                    System.out.println(res.stream().sorted().toList()); // in next version we will see if we can optimise this
                dpSet.add(key);
            }
            return;
        }
            res.add(arr[i]);
            funcComb(i+1, arr, n, target - arr[i], res);
            res.removeLast();
            funcComb(i+1, arr, n, target, res);
    }
}
