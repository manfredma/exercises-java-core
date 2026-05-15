package manfred.exercises.lang.basic.util;

import java.util.BitSet;

/**
 * 演示 Java BitSet 的位运算操作。
 *
 * 构造两个按不同规则设置位的 BitSet，依次执行 AND、OR、XOR 操作，
 * 直观展示位集合的交集、并集与对称差运算，
 * 帮助理解 BitSet 在内存高效标记与集合运算场景中的用法。
 */
public class BitSetDemo {

    public static void main(String args[]) {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        // set some bits
        for (int i = 0; i < 16; i++) {
            if ((i % 2) == 0)
                bits1.set(i);
            if ((i % 5) != 0)
                bits2.set(i);
        }
        System.out.println("Initial pattern in bits1: ");
        System.out.println(bits1);
        System.out.println("\nInitial pattern in bits2: ");
        System.out.println(bits2);

        // AND bits
        bits2.and(bits1);
        System.out.println("\nbits2 AND bits1: ");
        System.out.println(bits2);

        // OR bits
        bits2.or(bits1);
        System.out.println("\nbits2 OR bits1: ");
        System.out.println(bits2);

        // XOR bits
        bits2.xor(bits1);
        System.out.println("\nbits2 XOR bits1: ");
        System.out.println(bits2);
    }
}