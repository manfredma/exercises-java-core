package manfred.exercises.concurrency.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示并发场景下非线程安全集合的使用问题。
 *
 * 通过 ArrayList 在单线程环境中的 toArray 操作，对比潜在的并发修改风险，
 * 展示 ConcurrentModificationException 的触发条件以及安全迭代的注意事项。
 */
public class ConcurrentCollectionDemo {

    static List<Integer> x = new ArrayList<>();

    public static void main(String[] args) {
        x.add(1);
        x.add(2);
        Integer[] b = new Integer[x.size()];
        // startDel();
        Integer[] a = x.toArray(b);
        for (Integer integer : a) {
            System.out.println(integer);
        }
    }

    private static void startDel() {
        x.remove(1);
    }

}
