import java.util.Objects;

public class PersonWrapper {

    private final Person person;

    public PersonWrapper(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonWrapper that = (PersonWrapper) o;
        return Objects.equals(person.getAge(), that.person.getAge()) &&
                Objects.equals(person.getName(), that.person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(person);
    }
}
