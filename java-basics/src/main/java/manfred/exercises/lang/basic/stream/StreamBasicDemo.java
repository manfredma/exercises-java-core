package manfred.exercises.lang.basic.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 演示 Stream 的 flatMap 合并嵌套集合与 Comparator 多字段排序。
 *
 * 包含使用 flatMap(Collection::stream) 将 List&lt;List&lt;String&gt;&gt; 展开为一维列表，
 * 以及 Comparator.comparing().thenComparing() 链式排序时对 null 字段抛出
 * NullPointerException 的行为验证。
 */
public class StreamBasicDemo {
    @Test
    public void mergeMapValuesTest() {
        List<List<String>> xx = new ArrayList<>();
        List<String> xx1 = new ArrayList<>();
        xx1.add("xxx");
        xx.add(xx1);

        List<String> xxS = xx.stream().flatMap(Collection::stream).collect(Collectors.toList());

        System.out.println(xxS);
    }

    @Test
    public void testException() {
        A a1 = new A();
        A a2 = new A();
        List<A> aList = new ArrayList<>();
        aList.add(a1);
        aList.add(a2);
        aList = aList.stream()
                .sorted(
                        Comparator
                                .comparing(A::getX1)
                                .thenComparing(A::getX2))
                .collect(Collectors.toList());
    }

    @Test
    public void testException2() {
        A a1 = new A();
        a1.x1 = 3;
        A a2 = new A();
        a2.x2 = 4;
        List<A> aList = new ArrayList<>();
        aList.add(a1);
        aList.add(a2);
        aList = aList.stream()
                .sorted(
                        Comparator
                                .comparing(A::getX1)
                                .thenComparing(A::getX2))
                .collect(Collectors.toList());
    }

    class A {
        Integer x1;
        Integer x2;

        public Integer getX1() {
            System.out.println("return x1");
            return x1;
        }

        public Integer getX2() {
            System.out.println("return x2");
            return x2;
        }
    }
}
