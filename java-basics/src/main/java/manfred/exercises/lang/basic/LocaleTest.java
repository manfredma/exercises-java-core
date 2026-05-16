package manfred.exercises.lang.basic;

import java.util.Locale;

/**
 * @author Manfred since 2019/7/18
 */

public class LocaleTest {
    public static void main(String[] args) throws Exception {
        new LocaleTest().testIndonesia();
    }

    public void testIndonesia() {

        String s = "id_ID";
        String[] x = s.split("_");
        System.out.println(new Locale(x[0], x[1]));
        System.out.println(new Locale(x[0], x[1]).getLanguage());
        System.out.println(new Locale(x[0], x[1]).getISO3Language());
        System.out.println(new Locale(x[0], x[1]).toLanguageTag());

    }
}
