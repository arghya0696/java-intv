import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
// import java.lang.reflect.Modifier; // Uncomment if you want to exclude static fields
import java.util.HashMap;
import java.util.LinkedHashMap; // Used to maintain a more predictable order of fields
import java.util.Map;

/**
 * Utility class to compare objects by extracting and storing their field values.
 */
public class ObjectFieldExtractor {

    /**
     * Extracts all field values (including inherited ones) from two objects
     * and stores them in a structured map.
     *
     * @param obj1 The first object.
     * @param obj2 The second object.
     * @param <T>  The type of the objects. This allows passing any type of object.
     * If obj1 and obj2 are of different types, T will be their common superclass (e.g., Object).
     * @return A Map where keys "object1" and "object2" map to their respective field-value maps.
     * Each field-value map contains field names as keys and their corresponding values.
     * @throws IllegalAccessException If a field is inaccessible despite attempts to make it accessible
     * (e.g., due to security manager restrictions not overridden by setAccessible).
     */
    public static <T> Map<String, Map<String, Object>> extractFieldsFromObjects(T obj1, T obj2)
            throws IllegalAccessException {
        Map<String, Map<String, Object>> result = new HashMap<>();
        result.put("object1", extractFields(obj1));
        result.put("object2", extractFields(obj2));
        return result;
    }

    /**
     * Helper method to extract all fields and their values from a single object.
     * This includes fields from its superclasses up to (but not including) Object.class.
     * Fields are stored in a LinkedHashMap to preserve the order of declaration within a class
     * and the hierarchy (subclass fields first, then superclass fields).
     *
     * @param obj The object from which to extract fields.
     * @return A Map of field names to field values. Returns an empty map if the input object is null.
     * @throws IllegalAccessException If accessing a field's value fails.
     */
    private static Map<String, Object> extractFields(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return new HashMap<>(); // Return an empty map for null objects
        }

        // Using LinkedHashMap to maintain field order:
        // - Fields from the most specific class appear first.
        // - Within a class, fields appear in their declaration order (approximately).
        Map<String, Object> fieldsMap = new LinkedHashMap<>();
        Class<?> currentClass = obj.getClass();

        // Iterate up the class hierarchy to include inherited fields
        while (currentClass != null && currentClass != Object.class) {
            Field[] declaredFields = currentClass.getDeclaredFields();
            for (Field field : declaredFields) {
                // Optional: Uncomment to skip static fields
                // if (Modifier.isStatic(field.getModifiers())) {
                //     continue;
                // }

                try {
                    // Make the field accessible, even if it's private/protected.
                    // This can throw InaccessibleObjectException (a RuntimeException) in Java 9+
                    // if the module containing the field does not 'open' its package to this module.
                    // It can also throw SecurityException if a SecurityManager is active and denies access.
                    field.setAccessible(true);
                } catch (InaccessibleObjectException | SecurityException e) {
                    System.err.println("Warning: Could not make field accessible: " +
                            field.getDeclaringClass().getName() + "." + field.getName() +
                            ". Error: " + e.getMessage() + ". Skipping this field.");
                    continue; // Skip this field if it cannot be made accessible
                }

                // Get the field's value. This can throw IllegalAccessException if, despite
                // setAccessible(true), access is still denied (e.g. final static fields of
                // primitive types in certain JDK internal classes, though rare for typical user classes).
                Object value = field.get(obj);

                // putIfAbsent ensures that if a field is shadowed (redefined in a subclass),
                // the value from the most specific class (subclass) is stored.
                // Subclass fields are processed before superclass fields due to the loop structure.
                fieldsMap.putIfAbsent(field.getName(), value);
            }
            currentClass = currentClass.getSuperclass();
        }
        return fieldsMap;
    }

    // --- Example Usage ---

    // Base class for demonstration
    static class Person {
        private String name; // Private field
        private int age;     // Private field
        protected String species = "Homo sapiens"; // Protected field

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            // This toString is for basic representation; reflection will access fields directly.
            return "Person{name='" + name + "', age=" + age + ", species='" + species + "'}";
        }
    }

    // Derived class for demonstration
    static class Student extends Person {
        private String studentId; // Private field in subclass
        public static String university = "Default University"; // Static field
        private final String program; // Final field
        protected String species = "Homo sapiens (student)"; // Shadowing superclass field

        public Student(String name, int age, String studentId, String program) {
            super(name, age);
            this.studentId = studentId;
            this.program = program;
        }

        public void setSpecies(String species) {
            this.species = species;
        }

        @Override
        public String toString() {
            // This toString is for basic representation.
            // The extractFields method will get all relevant fields via reflection.
            return "Student{studentId='" + studentId + "', program='" + program +
                    "', university='" + university + "'} extends " + super.toString();
        }
    }

    public static void main(String[] args) {
        Person person1 = new Person("Alice Wonderland", 30);
        Student student1 = new Student("Bob The Builder", 22, "S12345", "Computer Science");
        Student.university = "Tech University"; // Modify static field

        Student student2 = new Student("Charlie Chaplin", 25, "S67890", "Film Studies");
        student2.setSpecies("Homo sapiens (creative)"); // Modify shadowed field for this instance

        try {
            System.out.println("--- Comparing Person and Student ---");
            Map<String, Map<String, Object>> comparison1 = extractFieldsFromObjects(person1, student1);
            printFormattedResult(comparison1);

            System.out.println("\n--- Comparing two Student objects (one with modified static and instance fields) ---");
            Map<String, Map<String, Object>> comparison2 = extractFieldsFromObjects(student1, student2);
            printFormattedResult(comparison2);

            System.out.println("\n--- Comparing a Student and a null object ---");
            Map<String, Map<String, Object>> comparison3 = extractFieldsFromObjects(student1, null);
            printFormattedResult(comparison3);

        } catch (IllegalAccessException e) {
            System.err.println("\nError: An issue occurred while accessing object fields via reflection.");
            e.printStackTrace();
        }
    }

    private static void printFormattedResult(Map<String, Map<String, Object>> comparisonResult) {
        comparisonResult.forEach((objectKey, fieldsMap) -> {
            System.out.println("\nFields for \"" + objectKey + "\":");
            if (fieldsMap.isEmpty()) {
                System.out.println("  (Object was null or no fields were extractable)");
            } else {
                fieldsMap.forEach((fieldName, fieldValue) -> {
                    String valueType = (fieldValue != null) ? fieldValue.getClass().getSimpleName() : "null";
                    System.out.printf("  - %-20s: %-30s (%s)%n%n", fieldName, fieldValue, valueType);
                });
            }
        });
    }
}
