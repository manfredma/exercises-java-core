package manfred.exercises.lang.dsl;

import manfred.exercises.lang.stream.model.Dish;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;

import static java.util.stream.Collectors.groupingBy;
import static manfred.exercises.lang.stream.model.Dish.menu;
import static manfred.exercises.lang.dsl.Grouping.GroupingBuilder.groupOn;

/**
 * 演示使用 Stream Collectors 实现多级分组，以及自定义 GroupingBuilder DSL。
 *
 * 通过 groupingBy 嵌套实现按菜品类型和热量等级的二级分组，
 * 并引入 GroupingBuilder 构建器模式，以链式 API（.after().get()）
 * 替代原始的嵌套 groupingBy，体现 DSL 风格简化复杂 Collector 组合的思路。
 */
public class Grouping {

    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String ... args) {
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel2());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel3());
    }

    private static CaloricLevel getCaloricLevel( Dish dish ) {
        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel2() {
        return menu.stream().collect(
                twoLevelGroupingBy(Dish::getType, dish -> getCaloricLevel( dish ) )
                                    );
    }

    public static <A, B, T> Collector<T, ?, Map<A, Map<B, List<T>>>> twoLevelGroupingBy(Function<? super T, ? extends A> f1, Function<? super T, ? extends B> f2) {
        return groupingBy(f1, groupingBy(f2));
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel3() {
        Collector<? super Dish, ?, Map<Dish.Type, Map<CaloricLevel, List<Dish>>>> c = groupOn( ( Dish dish ) -> getCaloricLevel( dish ) ).after( Dish::getType ).get();
        return menu.stream().collect( c );
    }

    public static class GroupingBuilder<T, D, K> {
        private final Collector<? super T, ?, Map<K, D>> collector;

        public GroupingBuilder( Collector<? super T, ?, Map<K, D>> collector ) {
            this.collector = collector;
        }

        public Collector<? super T, ?, Map<K, D>> get() {
            return collector;
        }

        public <J> GroupingBuilder<T, Map<K, D>, J> after(Function<? super T, ? extends J> classifier) {
            return new GroupingBuilder<>( groupingBy( classifier, collector ) );
        }

        public static <T, D, K> GroupingBuilder<T, List<T>, K> groupOn(Function<? super T, ? extends K> classifier) {
            return new GroupingBuilder<>( groupingBy( classifier )  );
        }
    }
}