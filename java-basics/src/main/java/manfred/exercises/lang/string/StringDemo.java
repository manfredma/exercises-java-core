package manfred.exercises.lang.string;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 综合演示 Java {@link String} 常用 API 的行为与边界情况。
 *
 * 涵盖以下知识点：{@code charAt} 索引取字符、对象与字符串 {@code +} 拼接时隐式调用
 * {@code toString()} 产生新 String 实例、{@link StringBuffer#reverse()} 反转、
 * UTF-8 与 GBK 编码互转导致的乱码现象、带转义符的 {@code split} 正则分隔符用法、
 * {@code null} 与字符串拼接输出字面量 {@code "null"}、{@code substring} 从尾部截取，
 * 以及通过中间占位符绕开 {@code replace} 无法直接替换反斜杠的技巧。
 */
public class StringDemo {

    public static void main(String[] args) throws Exception {
        StringDemo t = new StringDemo();
        t.charAt();
        t.stringOperator();
        t.reverse();
        t.encode();
        t.split();
        t.plus();
        t.subString();
        t.replace();
    }

    /**
     * 测试下charAt
     */
    public void charAt() {
        String a = "012345";
        System.out.println("charAt(1): " + a.charAt(1) + ", expected: 1");
    }

    public void stringOperator() {
        Object a = new Object() {
            @Override
            public String toString() {
                return "parent.toString";
            }
        } + "~~xxx";
        System.out.println("class: " + a.getClass() + ", expected: " + String.class);
        System.out.println("toString: " + a.toString() + ", expected: parent.toString~~xxx");
    }

    public void reverse() {
        System.out.println("reverse: " + new StringBuffer("xxxxxxb").reverse().toString() + ", expected: bxxxxxx");
    }

    public void encode() throws UnsupportedEncodingException {
        System.out.println("淡淡的蓝20106");
        System.out.println(new String("淡淡的蓝20106".getBytes("UTF-8"), "GBK"));
        System.out.println(new String("张Qq-_".getBytes("UTF-8"), "GBK"));
        System.out.println(new String("金戈小虔".getBytes("UTF-8"), "GBK"));
    }

    public void split() {
        String aString = "ad_x_2";
        String[] splitResult = aString.split("_");
        for (int i = 0; i < splitResult.length; i++) {
            System.out.print(splitResult[i] + " ");
        }
        System.out.println();

        aString = "fja;f?df;akf?:kjfas;lf'";
        splitResult = aString.split("\\?");
        System.out.println(Arrays.toString(splitResult));
    }

    public void plus() {
        System.out.println("xxx" + null + "zzz");
    }

    public void subString() {
        String string = "ur1092345780";
        System.out.println(string.substring(string.length() - 3));
    }

    public void replace() {

        String st = "\\\\";
        System.out.println(st.replace("\\\\", "!!!!!￥￥￥￥￥$$$!!!!")
                .replace("\\", "").replace("!!!!!￥￥￥￥￥$$$!!!!", "\\"));
        st = "\\hellofasjf\\flkaj\\";
        System.out.println(st.replace("\\\\", "!!!!!￥￥￥￥￥$$$!!!!")
                .replace("\\", "").replace("!!!!!￥￥￥￥￥$$$!!!!", "\\"));
        st = "\\\"";
        System.out.println(st.replace("\\\\", "!!!!!￥￥￥￥￥$$$!!!!")
                .replace("\\", "").replace("!!!!!￥￥￥￥￥$$$!!!!", "\\"));
    }

}
