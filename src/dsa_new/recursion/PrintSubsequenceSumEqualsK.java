package dsa_new.recursion;

import java.util.ArrayList;

public class PrintSubsequenceSumEqualsK {

    static int k = 8;

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};

        int sum = 0;
        printSubsequenceSumEqualsK(0, arr, new ArrayList<>(), sum);

        System.out.println(checkSubsequenceEqualToK(0, arr, sum));
    }

    private static void printSubsequenceSumEqualsK(int index, int[] arr, ArrayList<Integer> list, int sum) {

        if(index >= arr.length) {
            if(sum == k)
                System.out.println(list);
            return;
        }

        sum+=arr[index]; // take
        list.add(arr[index]); // take
        printSubsequenceSumEqualsK(index+1, arr, list, sum);

        sum-=arr[index]; // not take
        list.removeLast(); // not take
        printSubsequenceSumEqualsK(index+1, arr, list, sum);
    }

    private static boolean checkSubsequenceEqualToK(int index, int[] arr, int sum) {

        if(index >= arr.length) {
            return sum == k;
        }

        sum+=arr[index];
        boolean a = checkSubsequenceEqualToK(index + 1, arr, sum);
        sum-=arr[index];
        boolean b = checkSubsequenceEqualToK(index + 1, arr, sum);

        return (a||b);
    }
}
