package manfred.exercises.lang.string;

import java.util.Scanner;

/**
 * 演示字符串规范化处理：将任意文本转换为合法的 Java 全限定类名片段。
 *
 * {@code generateNameWithMain} 方法依次执行小写转换、空格替换为点、
 * 去除非字母数字和点的特殊字符、合并连续双点，并追加固定后缀类名，
 * 综合运用了 {@link String#toLowerCase()}、{@link String#replace(CharSequence, CharSequence)}、
 * {@link Character#isLetterOrDigit(char)} 和 {@link StringBuffer} 的字符过滤，
 * 是练习字符串逐字符处理与多步链式替换的典型示例。
 */
public class ClassName {
    public static String generateNameWithMain(String s, String prefix) {
        s = s.toLowerCase();
        s = s.replace(" ", ".");
        s = s.replace("..", ".");
        s = s + ".ClassInitOrderDemo";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i)) || '.' == (s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
        s = sb.toString();
        s = s.replace("..", ".");
        s = prefix + s;
        return s;
    }

    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            while (true) {
                System.out.println(generateNameWithMain(s.nextLine(), "exe"));
            }
        } catch (Exception e) {

        }
    }
}
