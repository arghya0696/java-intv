import java.util.*;
public class HmpMain {
    public static void main(String[] args) {

        MyHmp<String, Integer> myHmp = new MyHmp<>(4);

        myHmp.put("rivu", 10);
        myHmp.put("rima", 12);
        myHmp.put("rik", 11);
        myHmp.put("abc", 22);
        myHmp.put("rivu", 20);
        myHmp.put("xyz", 33);

        myHmp.print();

        System.out.println(myHmp.get("xyz"));

        myHmp.remove("xyz");

        System.out.println();

        myHmp.print();


    }
}
