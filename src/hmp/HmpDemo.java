package hmp;

import java.util.HashMap;
import java.util.Map;

public class HmpDemo {

    public static void main(String[] args) {

        Map<PayOutSubType, Customer> hmp = new HashMap<>();

        hmp.put(PayOutSubType.INTEREST, new Customer("test1"));
        hmp.put(PayOutSubType.MATURITY, new Customer("test2"));

        System.out.println("first"+ hmp);

        hmp.put(PayOutSubType.INTEREST, new Customer("test2"));

        System.out.println("first"+ hmp);

    }
}
record Customer(String name){}
