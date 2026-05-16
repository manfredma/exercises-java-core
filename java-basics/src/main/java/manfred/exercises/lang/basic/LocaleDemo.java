package manfred.exercises.lang.basic;

import java.util.Locale;

/**
 * 演示 Java {@link java.util.Locale} 的构造与语言标签解析。
 *
 * 以印度尼西亚语区域（id_ID）为例，展示如何将 language_COUNTRY 格式的字符串拆分后
 * 构造 Locale 对象，并分别通过 {@code getLanguage()}、{@code getISO3Language()}、
 * {@code toLanguageTag()} 获取两字母代码、三字母 ISO 639-2 代码及 BCP 47 语言标签，
 * 帮助理解不同 Locale API 方法返回值的含义与格式差异。
 */
public class LocaleDemo {
    public static void main(String[] args) throws Exception {
        new LocaleDemo().testIndonesia();
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
