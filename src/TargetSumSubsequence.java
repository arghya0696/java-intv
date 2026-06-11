public class TargetSumSubsequence {

    public static void main(String[] args) {

        int[] arr = {2, 3, 1, 1};
        int target = 2;

        System.out.println(recFromLast(arr, arr.length - 1, target));

        System.out.println(recFromBig(arr, 0, 0, target));
    }

    private static boolean recFromLast(int[] arr, int i, int target) {

        if (target == 0) return true;
        if (i == 0) return (arr[i] == target);

        boolean notTake = recFromLast(arr, i - 1, target);
        boolean take = false;

        if (target >= arr[i]) {
            take = recFromLast(arr, i - 1, target - arr[i]);
        }

        return (notTake || take);
    }

    private static boolean recFromBig(int[] arr, int i, int sum, int target) {

        if (sum == target) return true;
        if (i == arr.length - 1) return (arr[i] == target);

        boolean notTake = recFromBig(arr, i + 1, arr[i], target);
        boolean take = false;

        if (target >= arr[i]) {
            take = recFromBig(arr, i + 1, sum + arr[i], target);
        }

        return (notTake || take);
    }
}
