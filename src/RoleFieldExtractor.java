import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Modifier;

/**
 * This class demonstrates how to use Java Reflection to find all fields
 * in a class that start with a specific prefix ("role") and get their values
 * in a type-safe, generic way.
 */
public class RoleFieldExtractor {

    /**
     * Retrieves the values of all fields from an object whose names start with a given prefix,
     * casting them to a specific type.
     *
     * @param object    The object from which to extract field values.
     * @param prefix    The prefix to match against field names.
     * @param valueType The class of the type to which the values should be cast.
     * @param <T>       The type of the input object.
     * @param <V>       The type of the values to be returned in the list.
     * @return A list of objects representing the values of the matching fields, cast to the specified type.
     */
    public static <T, V> List<V> getFieldValuesWithPrefix(T object, String prefix, Class<V> valueType) {
        List<V> values = new ArrayList<>();

        // Determine the class to inspect, which could be the object's class or the class itself if it's static
        Class<?> clazz = (object instanceof Class) ? (Class<?>) object : object.getClass();

        // Get all declared fields (including private, protected, and public)
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            // Check if the field name starts with the specified prefix
            if (field.getName().startsWith(prefix)) {
                try {
                    // Make the field accessible, even if it's private
                    field.setAccessible(true);

                    // Determine if the field is static
                    boolean isStatic = Modifier.isStatic(field.getModifiers());

                    // Get the value. If static, pass null for the object instance.
                    Object value = field.get(isStatic ? null : object);

                    // Check if the retrieved value is null or can be cast to the desired type
                    if (value == null || valueType.isInstance(value)) {
                        values.add(valueType.cast(value));
                        System.out.println("Found matching field: '" + field.getName() + "', Value: '" + value + "'");
                    } else {
                        // Handle cases where the field value is not of the expected type
                        System.err.println("Field '" + field.getName() + "' value is of type " +
                                value.getClass().getName() + " but expected " + valueType.getName() + ". Skipping.");
                    }
                } catch (IllegalAccessException e) {
                    // This might happen if a security manager prevents accessibility override.
                    System.err.println("Could not access field: " + field.getName());
                    e.printStackTrace();
                }
            }
        }
        return values;
    }

    public static void main(String[] args) {
        // 1. Create an instance of the class we want to inspect.
        UserRoles user = new UserRoles();

        System.out.println("Inspecting object: " + user.toString());
        System.out.println("-------------------------------------------------");
        System.out.println("Searching for fields starting with 'role' and casting to String...\n");

        // 2. Call the generic method, specifying that we expect String values from the instance.
        List<String> roleValues = getFieldValuesWithPrefix(user, "role", String.class);

        System.out.println("\n--- Searching for static fields ---");
        // We can also search for static fields by passing the Class object itself.
    }

    private static class UserRoles {
        private String role1 = "Administrator";
        private String role2 = "Editor";
        private String role3 = "Viewer";
        private String role4; // This one is null to show it's handled

        // Other fields that should be ignored
        private String username = "jdoe";
        private int userId = 123;
        public String publicInfo = "This is a public field.";
        private static final String CONSTANT_ROLE = "SUPER_ADMIN"; // Static fields are also handled

        /**
         * Constructor to initialize the UserRoles object.
         */
        public UserRoles() {
            // The fields are initialized with default values.
        }

        // Getters for demonstration, though not used by the reflection code
        public String getRole1() {
            return role1;
        }

        public String getRole2() {
            return role2;
        }

        public String getRole3() {
            return role3;
        }

        public String getUsername() {
            return username;
        }

        public int getUserId() {
            return userId;
        }

        @Override
        public String toString() {
            return "UserRoles{" +
                    "role1='" + role1 + '\'' +
                    ", role2='" + role2 + '\'' +
                    ", role3='" + role3 + '\'' +
                    ", role4='" + role4 + '\'' +
                    ", username='" + username + '\'' +
                    ", userId=" + userId +
                    '}';
        }
    }
}

