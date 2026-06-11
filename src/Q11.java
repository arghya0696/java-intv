import java.util.Arrays;

public class Q11 {

    public static void main(String[] args) {

        String str = "xxxrwppk";

        int[] hash = new int[26];

        for(int i=0;i<str.length();i++) {
            hash[str.charAt(i) - 'a']++;
        }

         int maxChar = 0, maxCharCount = 0;

        for(int i=0;i<26;i++) {
            if(hash[i] > maxCharCount) {
                maxCharCount = hash[i];
                maxChar = i;
            }
        }
        if(maxCharCount > (str.length()+1)/2) {
            System.out.println("not possible");
        } else {


            int idx = 0;
            Character[] res = new Character[str.length()];
            while (hash[maxChar]-- > 0) {
                res[idx] = (char) ( maxChar + 'a');
                idx+=2;
            }

            for (int i=0;i<26;i++) {
                while (hash[i]-- > 0) {
                    if(idx>=str.length())
                        idx = 1;
                    res[idx] = (char) (i + 'a');
                    idx+=2;
                }
            }

            System.out.println(Arrays.toString(res));

        }


    }
}
