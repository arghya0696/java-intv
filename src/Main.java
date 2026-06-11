import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {


        int[] arr1 = {3,6,2,1};

        int[] arr2 = {5,9,7,3,11};

        List<Integer> list = IntStream.concat(IntStream.of(arr1), IntStream.of(arr2)).boxed().sorted().limit(3).toList();

        System.out.println(list);


        // add a tree

        TreeNode root = new TreeNode(10);

        root.left = new TreeNode(8);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);


        System.out.println(root.left.right.value);

        // end
        int targetSum = 21;
        int sum = 0;

        System.out.println(pathSum(root, targetSum, sum));
    }

    private static boolean pathSum(TreeNode root, int targetSum, int sum) {

        if(root == null)
            return false;
        if(root.left == null && root.right == null) {
            sum+=root.value;
            if(sum == targetSum)
                return true;
        }
        return (pathSum(root.left, targetSum, sum+root.value) || pathSum(root.right, targetSum, sum+root.value));
    }
}