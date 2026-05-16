package manfred.exercises.lang.basic.other;

import java.util.UUID;

/**
 * @author Manfred since 2019/7/2
 */
public class UuidTest {

    public static void main(String[] args) throws Exception {
        new UuidTest().testCreate();
    }

    public void testCreate() {
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());

    }

    private static String createGUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

}
