package manfred.exercises.lang.basic.hash;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 演示 List 元素顺序对 hashCode 计算结果的影响。
 *
 * 通过构造两个元素相同但顺序不同的 ArrayList，
 * 验证 Objects.hash() 对有序集合的哈希值是顺序敏感的，
 * 加深对 hashCode 合约与集合相等性判断的理解。
 */
public class HashCode {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(12);
        integerList.add(11);
        List<Integer> integerList2 = new ArrayList<>();
        integerList2.add(11);
        integerList2.add(12);
        System.out.println(Objects.hash(integerList));
        System.out.println(Objects.hash(integerList2));
    }
}
