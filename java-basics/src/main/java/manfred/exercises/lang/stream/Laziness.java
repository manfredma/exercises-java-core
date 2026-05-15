package manfred.exercises.lang.stream;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
/**
 * 演示 Stream 的惰性求值特性。
 *
 * 在 filter 和 map 操作中插入打印语句，通过 limit(2) 触发短路求值，
 * 观察流水线只处理满足条件的最少元素，说明中间操作延迟执行直到终端操作被调用的机制。
 */
public class Laziness {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> twoEvenSquares =
                numbers.stream()
                       .filter(n -> {
                           System.out.println("filtering " + n); return n % 2 == 0;
                       })
                       .map(n -> {
                           System.out.println("mapping " + n);
                           return n * n;
                       })
                       .limit(2)
                       .collect(toList());

    }

}