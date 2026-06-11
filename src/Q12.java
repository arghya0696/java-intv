public class Q12 {

    public static void main(String[] args) {


        String str = "GOLDMAN SACHS";

        String[] words = str.split("\\s+");

        // reverse word
        int i=0,j=words.length-1;
        String temp = "";
        while (i<j) {
            temp = words[i];
            words[i] = words[j];
            words[j] = temp;
            i++;
            j--;
        }

        // now reverse each string

        for(int k=0;k< words.length;k++) {
            String[] data = words[k].split("");

            i = 0;
            j = data.length-1;
            temp = "";

            while (i<j) {
                temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                i++;
                j--;
            }
            words[k] = String.join("", data);
        }

        String joined = String.join(" ", words);

        System.out.println(joined);
    }
}
