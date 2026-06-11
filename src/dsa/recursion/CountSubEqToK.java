package dsa.recursion;

public class CountSubEqToK {

    static int k = 2;

    public static void main(String[] args) {

        int[] arr = {1,2,1};

        int ans = func(0, arr, arr.length, 0);
        System.out.println("count:"+ ans);
    }

    private static int func(int i, int[] arr, int n, int sum) {

        if(i>=n) {
            if(sum == k) {
              return 1;
            }
            return 0;
        }
        return (func(i+1, arr, n, sum+arr[i]) + func(i+1, arr, n, sum));
    }
}
