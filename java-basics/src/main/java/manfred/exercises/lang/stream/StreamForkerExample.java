package manfred.exercises.lang.stream;

import manfred.exercises.lang.stream.model.Dish;
import static manfred.exercises.lang.stream.model.Dish.menu;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.util.stream.*;

/**
 * 演示 StreamForker 将同一个流分发给多个消费者并行处理的用法。
 *
 * 通过 fork 注册四个不同操作（拼接名称、求总卡路里、找最高卡路里菜、按类型分组），
 * getResults 一次性触发执行并获取各操作结果，
 * 展示如何避免多次遍历同一流数据的性能损耗。
 */
public class StreamForkerExample {

    public static void main(String[] args) throws Exception {
        processMenu();
    }

    private static void processMenu() {
        Stream<Dish> menuStream = menu.stream();

        StreamForker.Results results = new StreamForker<>(menuStream)
                .fork("shortMenu", s -> s.map(Dish::getName).collect(joining(", ")))
                .fork("totalCalories", s -> s.mapToInt(Dish::getCalories).sum())
                .fork("mostCaloricDish", s -> s.collect(
                        reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
                        .get())
                .fork("dishesByType", s -> s.collect(groupingBy(Dish::getType)))
                .getResults();

        String shortMeny = results.get("shortMenu");
        int totalCalories = results.get("totalCalories");
        Dish mostCaloricDish = results.get("mostCaloricDish");
        Map<Dish.Type, List<Dish>> dishesByType = results.get("dishesByType");

        System.out.println("Short menu: " + shortMeny);
        System.out.println("Total calories: " + totalCalories);
        System.out.println("Most caloric dish: " + mostCaloricDish);
        System.out.println("Dishes by type: " + dishesByType);
    }
}