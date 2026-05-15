package manfred.exercises.lang.basic.system;

/**
 * @author Manfred since 2019/5/8
 */
public class SystemPropertiesDemo {
    public static void main(String[] args) {
        for (String stringPropertyName : System.getProperties().stringPropertyNames()) {
            System.out.println(stringPropertyName + "=" + System.getProperty(stringPropertyName));
        }
    }
}
