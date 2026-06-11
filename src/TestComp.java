import java.util.HashSet;
import java.util.Set;

public class TestComp {

    public static void main(String[] args) {

        Person person1  = new Person("p1", 10);

        Person person2  = new Person("p2", 10);


        final Set<PersonWrapper> personSet1 = Set.of(new PersonWrapper(person1));

        final Set<PersonWrapper> personSet2 = Set.of(new PersonWrapper(person2));

        boolean equals = true;

        personSet1.equals(personSet2);

        for(PersonWrapper person : personSet1) {
            if(!personSet2.contains(person)) {
                equals = false;
                break;
            }
        }

        System.out.println(equals);
    }
}
