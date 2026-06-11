import java.util.*;
import java.util.stream.Collectors;

public class Q5 {
    public static void main(String[] args) {

        String[] input = {"cat", "dog", "god", "cat"};


        Map<String, List<String>> collect = Arrays.stream(input).collect(Collectors.groupingBy(
                Q5::sortString
        ));

        System.out.println(collect.values());
    }

    private static String sortString(String str) {

        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);

        return String.valueOf(charArray);
    }


}
