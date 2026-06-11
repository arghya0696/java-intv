import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Q7 {

    public static void main(String[] args) {

        String[][] input = {
                {"Charles", "65"},
                {"Charles", "100"},
                {"John", "70"},
                {"Charles", "61"}
        };

       doAvg(input);


    }

    private static void doAvg(String[][] input) {

        List<Student> stdList = new ArrayList<>();

        for (String[] strings : input) {
            stdList.add(new Student(strings[0], Integer.parseInt(strings[1])));
        }


        // cal avg marks of each student
        Map<String, Double> avgMap = stdList.stream()
                .collect(Collectors.groupingBy(Student::getName, Collectors.averagingInt(Student::getNumber)));

        String maxAvgName = avgMap.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).get();

        System.out.println(avgMap);

        System.out.println(maxAvgName);


    }

    private static class Student {
        String name;
        Integer number;

        public Student(String name, Integer number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }
    }

}
