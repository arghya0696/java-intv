package dsa_new.recursion;

import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {

        //generateParenthesis(3, new ArrayList<>(), 0, 0);
        generateParenthesis2(3, "", 0, 0);
    }

    public static void generateParenthesis(int n, List<String> result, int left, int right) {

        if(left == right) {
            String validString = String.join("", result);
            if(validString.length() >= 2*n)
                System.out.println(validString);
        }

        // take left
        if(left < n) {
            result.add("(");
            generateParenthesis(n, result, left+1, right);
            result.removeLast();
        }

        // take right
        if(left > right) {
            result.add(")");
            generateParenthesis(n, result, left, right+1);
            result.removeLast();
        }
    }

    public static void generateParenthesis2(int n, String result, int open , int close) {
        if(open == close && (open+close) == 2*n) {
            System.out.println(result);
        }

        if(open < n) {
            generateParenthesis2(n, result+"(", open+1, close);
        }

        if(open > close) {
            generateParenthesis2(n, result+")", open, close+1);
        }
    }
}
