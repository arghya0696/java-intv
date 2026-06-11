import jdk.jfr.Frequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q2 {
    public static void main(String[] args) {
        String []dictionary = {"to", "banana", "toes", "dogs", "ababcd", "elephant"};
        String input = "ogtdes";

        List<String> result = getLargestWord(dictionary,input);
        System.out.println(result);
    }

    private static List<String> getLargestWord(String[] dictionary, String input) {
        List<String> result = new ArrayList<>();
        int []frequency = new int[26];
        int maxLength = Integer.MIN_VALUE;

        for(Character c : input.toCharArray())
            frequency[c-'a']++;

        for(String word : dictionary){
            if(wordCanBeMade(frequency.clone(),word)){
                if(word.length()==maxLength)
                    result.add(word);
                else if (word.length()>maxLength) {
                    maxLength=word.length();
                    result.clear();
                    result.add(word);
                }
            }
        }

        return result;
    }

    private static boolean wordCanBeMade(Object clone, String word) {
        int []frequencyMap = (int[]) clone;
        for(Character c : word.toCharArray()){
            if(frequencyMap[c-'a']==0)
                return false;
            else
                frequencyMap[c-'a']--;
        }
        return true;
    }
}
