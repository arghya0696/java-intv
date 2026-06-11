import java.util.Stack;

public class Q26 {
    public static void main(String[] args) {


        String str = "goldmanfg**ss*achsd*";

        Stack<Character> characterStack = new Stack<>();

        for(Character ch : str.toCharArray()) {

            if(!characterStack.isEmpty() && ch == '*') {
                characterStack.pop();
            } else
                characterStack.add(ch);
        }

        StringBuilder ans = new StringBuilder();

        for (Character ch : characterStack) {
            ans.append(ch);
        }

        System.out.println(ans);
    }
}
