package manfred.exercises.lang.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import static java.util.stream.Collector.Characteristics.*;

/**
 * 自定义收集器实现：将流元素收集为 List，等价于内置的 Collectors.toList()。
 *
 * 通过实现 Collector 接口的五个方法（supplier/accumulator/finisher/combiner/characteristics）
 * 演示自定义收集器的完整结构，特性标记 IDENTITY_FINISH 和 CONCURRENT 表示结果无需转换且支持并发。
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    @Override
    public Supplier<List<T>> supplier() {
        return () -> new ArrayList<T>();
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return (list, item) -> list.add(item);
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return i -> i;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }
}